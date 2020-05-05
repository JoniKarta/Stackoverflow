package acs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acs.boundaries.ElementBoundary;
import acs.data.ElementEntity;
import acs.data.Creator;
import acs.data.ElementConverter;

@Service
public class ElementServiceWithDB implements ElementService {
	private ElementConverter entityCoverter;
	private ElementDao elementDao;

	@Autowired
	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
	}

	@Autowired
	public void setEntityCoverter(ElementConverter entityCoverter) {
		this.entityCoverter = entityCoverter;
	}

	@Override
	@Transactional
	public ElementBoundary createNewElement(String managerEmail, ElementBoundary input) {
		String newId = UUID.randomUUID().toString();
		ElementEntity newElement = this.entityCoverter.convertToEntity(input);
		newElement.setCreatedBy(new Creator(managerEmail));
		newElement.setElementId(newId);
		newElement.setCreation(new Date());
		
		newElement = this.elementDao.save(newElement);
		
		return this.entityCoverter.convertFromEntity(newElement);
	}

	@Override
	@Transactional
	public ElementBoundary updateElement(String managerEmail, String elementId, ElementBoundary update) {
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
		Optional<ElementEntity> entity = this.elementDao.findById(elementId);
		
		if (entity.isPresent()) {
			return this.entityCoverter.convertFromEntity(entity.get());
		}
		else {
			throw new ElementNotFoundException("Could not find element for id: " + elementId);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAllElements(String userEmail) {
	
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
}
