package acs.logic;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.boundaries.ActionBoundary;
import acs.data.ActionConverter;
import acs.data.ActionEntity;

@Service
public class ActionServiceMockup implements ActionService {
	private Map<Long, ActionEntity> database;
	private ActionConverter actionConverter;
	private AtomicLong nextId;

	public ActionServiceMockup() {
		System.err.println("Action service init");
	}

	@PostConstruct
	public void init() {
		database = Collections.synchronizedMap(new TreeMap<>());
		nextId = new AtomicLong(1L);
	}

	@Autowired
	public void setActionConverter(ActionConverter actionConverter) {
		this.actionConverter = actionConverter;
	}

	@Override
	public Object invokeAction(ActionBoundary action) {
		Long newId = nextId.getAndIncrement();
		ActionEntity entity = actionConverter.convertToEntity(action);
		entity.setActionId(newId);
		entity.setCreatedTimestamp(new Date());
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
