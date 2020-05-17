package acs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acs.boundaries.ActionBoundary;
import acs.dal.ActionDao;
import acs.dal.LastActionIdValue;
import acs.dal.LastActionValueDao;
import acs.data.ActionConverter;
import acs.data.ActionEntity;
import acs.logic.services.EnhancedActionService;
import acs.validations.InvalidActionElement;
import acs.validations.InvalidActionInvoker;
import acs.validations.InvalidActionType;
import acs.validations.Validator;

@Service
public class ActionServiceWithDB implements EnhancedActionService {
	private ActionDao actionDao;
	private ActionConverter actionConverter;
	private LastActionValueDao lastValueDao;
	private Validator validator;
	
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

	@Override
	@Transactional
	public Object invokeAction(ActionBoundary action) {
		if(!this.validator.validateActionElement(action)) {
			throw new InvalidActionElement("Action done on invalid element");
		}
		
		if(!this.validator.validateActionInvoker(action)) {
			throw new InvalidActionInvoker("Invalid action invoker");
		}
		
		if(!this.validator.validateActionType(action)) {
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
		actionDao.deleteAll();
	}

	@Override
	public List<ActionBoundary> getAllActions(String adminEmail, int size, int page) {
		return this.actionDao.findAll(
				PageRequest.of(page, size, Direction.ASC, "actionId"))
				.getContent()
				.stream()
				.map(entity->(ActionBoundary)this.actionConverter.convertFromEntity(entity))
				.collect(Collectors.toList());
	}

}