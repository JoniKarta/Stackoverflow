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
//import org.springframework.stereotype.Service;

import acs.boundaries.ElementBoundary;
import acs.data.ElementEntity;
import acs.logic.services.ElementService;
import acs.validations.ElementNotFoundException;
import acs.boundaries.data.Creator;
import acs.data.ElementConverter;

//@Service
public class ElementServiceMockup implements ElementService {
	private Map<Long, ElementEntity> database;
	private ElementConverter entityCoverter;
	private AtomicLong nextId;
	
	public ElementServiceMockup() {
	}

	@Autowired
	public void setEntityCoverter(ElementConverter entityCoverter) {
		this.entityCoverter = entityCoverter;
	}
	
	@PostConstruct
	public void init() {
		this.database = Collections.synchronizedMap(new TreeMap<>());
		this.nextId = new AtomicLong(0L);
	}

	@Override
	public ElementBoundary create(String managerEmail, ElementBoundary input) {
		Long newId = nextId.getAndIncrement();
		ElementEntity newElement = this.entityCoverter.convertToEntity(input);
		newElement.setCreatedBy(new Creator(managerEmail));
		newElement.setElementId(newId);
		newElement.setCreatedTimestamp(new Date());
		this.database.put(newId, newElement);
		return this.entityCoverter.convertFromEntity(newElement);
	}

	@Override
	public ElementBoundary update(String managerEmail, String elementId, ElementBoundary update) {
		ElementBoundary existing = this.getSpecificElement(managerEmail, elementId);
		boolean dirty = false;
		if (update.getType() != null) {
			existing.setType(update.getType());
			dirty = true;
		}

		if (update.getName() != null) {
			existing.setName(update.getName());
			dirty = true;
		}
		
		if(update.getCreatedBy() != null && update.getCreatedBy().getUserEmail() != existing.getCreatedBy().getUserEmail()) {
			existing.getCreatedBy().setUserEmail((update.getCreatedBy().getUserEmail()));
		}

		if (update.getActive() != null && update.getActive() != existing.getActive()) {
			existing.setActive(update.getActive());
			dirty = true;
		}

		if (update.getLocation() != null) {
			existing.setLocation(update.getLocation());
			dirty = true;
		}
		if (update.getElementAttribute() != null) {
			existing.setElementAttribute(update.getElementAttribute());
			dirty = true;
		}
		if (dirty) {
			this.database.put(this.entityCoverter.toEntityId(elementId), this.entityCoverter.convertToEntity(existing));
		}
		return existing;
	}

	@Override
	public ElementBoundary getSpecificElement(String userEmail, String elementId) {
		ElementEntity elementEntity = this.database.get(this.entityCoverter.toEntityId(elementId));
		if (elementEntity != null) {
			return this.entityCoverter.convertFromEntity(elementEntity);
		} else {
			throw new ElementNotFoundException("Could not find element with id: " + elementId);
		}
	}

	@Override
	public List<ElementBoundary> getAll(String userEmail) {
		return this.database.values().stream().map(entity -> this.entityCoverter.convertFromEntity(entity))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAllElements(String adminEmail) {
		this.database.clear();
	}
}
