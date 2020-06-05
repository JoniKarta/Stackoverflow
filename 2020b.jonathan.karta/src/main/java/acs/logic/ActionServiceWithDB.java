package acs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acs.boundaries.ActionBoundary;
import acs.dal.ActionDao;
import acs.dal.ElementDao;
import acs.dal.LastActionIdValue;
import acs.dal.LastActionValueDao;
import acs.dal.UserDao;
import acs.data.ActionConverter;
import acs.data.ActionEntity;
import acs.data.ElementConverter;
import acs.data.ElementEntity;
import acs.data.UserEntity;
import acs.logic.services.EnhancedActionService;
import acs.validations.ElementNotFoundException;
import acs.validations.InvalidActionInvoker;
import acs.validations.InvalidActionType;
import acs.validations.UserNotFoundException;
import acs.validations.Validator;

@Service
public class ActionServiceWithDB implements EnhancedActionService {
	private ActionDao actionDao;
	private ActionConverter actionConverter;
	private LastActionValueDao lastValueDao;
	private Validator validator;
	private UserDao userDao;
	private ElementDao elementDao;
	private ElementConverter elementConverter;

	@Autowired
	public ActionServiceWithDB(Validator validator) {
		this.validator = validator;
	}

	@Autowired
	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}

	@Autowired
	public void setActionConverter(ActionConverter actionConverter) {
		this.actionConverter = actionConverter;
	}
	
	@Autowired
	public void setElementConverter(ElementConverter elementConverter) {
		this.elementConverter = elementConverter;
	}

	@Autowired
	public void setLastValueDao(LastActionValueDao lastValueDao) {
		this.lastValueDao = lastValueDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
	}

	@Override
	@Transactional
	public Object invokeAction(ActionBoundary action) {

		UserEntity user = this.userDao.findById(action.getInvokedBy().getEmail()).orElseThrow(
				() -> new UserNotFoundException("Could not found user: " + action.getInvokedBy().getEmail()));
		
		if (!this.validator.isPlayer(user)) {
			throw new InvalidActionInvoker("You are not allowed for this kind of action");
		}
		if (action!= null && action.getType() != null && !action.getType().equals("Perimeter")) {
			Long elementId;
			try {
				if (!this.validator.validateActionElement(action)) {
					throw new ElementNotFoundException("Invalid element ID");
				}
				elementId = Long.parseLong(action.getElement().getElementId());
			} catch (NumberFormatException e) {
				throw new ElementNotFoundException("Invalid element ID");
			}

			ElementEntity element = this.elementDao.findById(elementId).orElseThrow(() -> new ElementNotFoundException(
					"Could not find element for id: " + action.getElement().getElementId()));

			if (!this.validator.isActive(element)) {
				throw new ElementNotFoundException("Could not find element for id: " + element.getElementId());
			}
		}

		if (!this.validator.validateActionType(action)) {
			throw new InvalidActionType("Invalid action type");
		}

		LastActionIdValue idValue = lastValueDao.save(new LastActionIdValue());
		ActionEntity entity = actionConverter.convertToEntity(action);
		entity.setActionId(idValue.getLastIdValue());
		entity.setCreation(new Date());
		lastValueDao.delete(idValue);
		entity = actionDao.save(entity);
		
		if (action.getType().equals("Perimeter")) {
			return elementDao
			.findAllByActiveAndLocation_latBetweenAndLocation_lngBetween(true,
					(Double) action.getActionAttributes().get("minLat"),
					(Double) action.getActionAttributes().get("maxLat"),
					(Double) action.getActionAttributes().get("minLng"),
					(Double) action.getActionAttributes().get("maxLng"))
			.stream().map(this.elementConverter::convertFromEntity)
			.collect(Collectors.toList());
		}
		
		return actionConverter.convertFromEntity(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ActionBoundary> getAllActions(String adminEmail) {
		List<ActionBoundary> rv = new ArrayList<>();
		Iterable<ActionEntity> entityList = this.actionDao.findAll();
		for (ActionEntity actionEntity : entityList) {
			rv.add((ActionBoundary) this.actionConverter.convertFromEntity(actionEntity));
		}
		return rv;
	}

	@Override
	@Transactional
	public void deleteAllActions(String adminEmail) {
		Optional<UserEntity> user = this.userDao.findById(adminEmail);
		if(!this.validator.isAdmin(user.get())){
			throw new InvalidActionInvoker("You are not allowed for this kind of action");
		}
		
		actionDao.deleteAll();
	}

	@Override
	public List<ActionBoundary> getAllActions(String adminEmail, int size, int page) {
		Optional<UserEntity> user = this.userDao.findById(adminEmail);
		if(!this.validator.isAdmin(user.get())){
			throw new InvalidActionInvoker("You are not allowed for this kind of action");
		}
		return this.actionDao.findAll(PageRequest.of(page, size, Direction.ASC, "actionId")).getContent().stream()
				.map(entity -> (ActionBoundary) this.actionConverter.convertFromEntity(entity))
				.collect(Collectors.toList());
	}
}