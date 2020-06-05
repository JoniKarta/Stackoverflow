package acs.logic.mockup;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acs.boundaries.ActionBoundary;
import acs.data.ActionConverter;
import acs.data.ActionEntity;
import acs.logic.services.ActionService;

//@Service
public class ActionServiceMockup implements ActionService {
	private Map<Long, ActionEntity> database;
	private ActionConverter actionConverter;
	private AtomicLong nextId;

	public ActionServiceMockup() {
	}

	@PostConstruct
	public void init() {
		database = Collections.synchronizedMap(new TreeMap<>());
		nextId = new AtomicLong(0L);
	}

	@Autowired
	public void setActionConverter(ActionConverter actionConverter) {
		this.actionConverter = actionConverter;
	}

	@Override
	public Object invokeAction(ActionBoundary action) {
		Long newId = nextId.getAndIncrement();
		ActionEntity entity = actionConverter.convertToEntity(action);
		entity.setActionId(Long.parseLong(newId.toString()));
		entity.setCreation(new Date());
		database.put(newId, entity);
		return actionConverter.convertFromEntity(entity);
	}

	@Override
	public List<ActionBoundary> getAllActions(String adminEmail) {
		return database.values().stream().map(entity -> (ActionBoundary) actionConverter.convertFromEntity(entity))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAllActions(String adminEmail) {
		database.clear();
	}

	
}
