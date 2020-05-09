package acs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acs.boundaries.ElementBoundary;
import acs.boundaries.ElementIdBoundary;
import acs.dal.ElementDao;
import acs.dal.LastIdValue;
import acs.dal.LastValueDao;
import acs.data.ElementEntity;
import acs.data.Creator;
import acs.data.ElementConverter;

@Service
public class ElementServiceWithDB implements EnhancedElementService {
	private ElementConverter entityCoverter;
	private ElementDao elementDao;
	private LastValueDao lastValueDao;
	
	@Autowired
	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
	}

	@Autowired
	public void setLastValueDao(LastValueDao lastValueDao) {
		this.lastValueDao = lastValueDao;
	}
	
	@Autowired
	public void setEntityCoverter(ElementConverter entityCoverter) {
		this.entityCoverter = entityCoverter;
	}

	@Override
	@Transactional
	public ElementBoundary create(String managerEmail, ElementBoundary element) {
		//String newId = UUID.randomUUID().toString();
		
		// Create new tupple in LastIdValue table with a non-used id
		LastIdValue elementId = this.lastValueDao.save(new LastIdValue());
		ElementEntity newElementEntity = 
				this.entityCoverter
					.convertToEntity(element);
		newElementEntity.setCreatedBy(new Creator(managerEmail));
		newElementEntity.setElementId(elementId.getLastIdValue());
		newElementEntity.setCreatedTimestamp(new Date());
		
		// Delete the tupple from the LastIdValue table
		this.lastValueDao.delete(elementId);
		newElementEntity = this.elementDao.save(newElementEntity);
		
		return this.entityCoverter.convertFromEntity(newElementEntity);
	}

	@Override
	@Transactional
	public ElementBoundary update(String managerEmail, String elementId, ElementBoundary update) {
		ElementBoundary existing = this.getSpecificElement(managerEmail, elementId);
		// Note there are 2 attributes that not gets updated (elemendId,Date)
		if (update.getType() != null) {
			existing.setType(update.getType());
		}

		if (update.getName() != null) {
			existing.setName(update.getName());
		}

		if (update.getCreatedBy() != null
				&& update.getCreatedBy().getUserEmail() != existing.getCreatedBy().getUserEmail()) {
			existing.getCreatedBy().setUserEmail((update.getCreatedBy().getUserEmail()));
		}

		if (update.getActive() != null && update.getActive() != existing.getActive()) {
			existing.setActive(update.getActive());
		}

		if (update.getLocation() != null) {
			existing.setLocation(update.getLocation());
		}
		if (update.getElementAttribute() != null) {
			existing.setElementAttribute(update.getElementAttribute());
		}
		
		this.elementDao.save(this.entityCoverter.convertToEntity(existing));
		
		return existing;
	}

	@Override
	@Transactional(readOnly = true)
	public ElementBoundary getSpecificElement(String userEmail, String elementId) {
		Optional<ElementEntity> entity = this.elementDao
				.findById(this.entityCoverter.toEntityId(elementId));
		
		if (entity.isPresent()) {
			return this.entityCoverter.convertFromEntity(entity.get());
		}
		else {
			throw new ElementNotFoundException("Could not find element for id: " + elementId);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAll(String userEmail) {
	
		List<ElementBoundary> rv = new ArrayList<>();
		
		Iterable<ElementEntity> content = this.elementDao.findAll();
		
		for (ElementEntity element : content) {
			rv.add(this.entityCoverter.convertFromEntity(element));
		}
		return rv;
	}

	@Override
	@Transactional
	public void deleteAllElements(String adminEmail) {
		// TODO Need to check if the mail belongs to an admin!
		this.elementDao.deleteAll();
	}

	@Override
	@Transactional
	public void bindElements(String managerEmail, String parentElementId, ElementIdBoundary input) {
		ElementEntity parent = this.elementDao.findById(this.entityCoverter.toEntityId(parentElementId))
		.orElseThrow(()-> new ElementNotFoundException("Could not find element for id: " + parentElementId));
		
		ElementEntity child = this.elementDao.findById(this.entityCoverter.toEntityId(input.getId()))
				.orElseThrow(()-> new ElementNotFoundException("Could not find element for id: " + parentElementId));
		
		parent.addChild(child);
		this.elementDao.save(parent);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ElementBoundary> getAllChildrens(String userEmail, String parentElementId) {
		
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ElementBoundary> getAllParents(String userEmail, String childElementId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	// TODO Implementation of enhanced element service interface

	

	
}
