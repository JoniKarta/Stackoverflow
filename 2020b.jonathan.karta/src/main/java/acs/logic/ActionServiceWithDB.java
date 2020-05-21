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
import acs.data.ElementEntity;
import acs.data.UserEntity;
import acs.logic.services.EnhancedActionService;
import acs.validations.ElementNotFoundException;
import acs.validations.InvalidActionElement;
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
		Optional<UserEntity> user = this.userDao.findById(action.getInvokedBy().getEmail());
		Optional<ElementEntity> element = this.elementDao.findById(Long.parseLong(action.getElement().getElementId()));
		if (!user.isPresent()) {
			throw new UserNotFoundException("Could not find user with email: " + user.get().getEmail());
		}

		if (!this.validator.isPlayer(user.get())) {
			throw new InvalidActionInvoker("You are not allowed for this kind of action");
		}

		if (!element.isPresent() || !element.get().getActive()) {
			throw new ElementNotFoundException("Could not find element for id: " + element.get().getElementId());
		}

		if (!this.validator.validateActionElement(action)) {
			throw new InvalidActionElement("Action done on invalid element");
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