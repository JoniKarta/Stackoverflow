package acs.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acs.boundaries.ElementBoundary;
import acs.boundaries.ElementIdBoundary;
import acs.boundaries.UserBoundary;
import acs.dal.ElementDao;
import acs.dal.LastElementIdValue;
import acs.dal.LastElementValueDao;
import acs.data.Creator;
import acs.data.ElementConverter;
import acs.data.ElementEntity;
import acs.logic.services.EnhancedElementService;
import acs.validations.ElementNotFoundException;
import acs.validations.InvalidElementName;
import acs.validations.InvalidElementType;
import acs.validations.Validator;

// TODO : Add player/manager role check in all functions
@Service
public class ElementServiceWithDB implements EnhancedElementService {
	private ElementConverter entityConverter;
	private ElementDao elementDao;
	private LastElementValueDao lastValueDao;
	private Validator validator;
	private UserServiceWithDB userService;

	@Autowired
	public ElementServiceWithDB(Validator validator) {
		this.validator = validator;
	}
	
	@Autowired
	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
	}

	@Autowired
	public void setLastValueDao(LastElementValueDao lastValueDao) {
		this.lastValueDao = lastValueDao;
	}

	@Autowired
	public void setEntityCoverter(ElementConverter entityCoverter) {
		this.entityConverter = entityCoverter;
	}

	@Autowired
	public void setUserService(UserServiceWithDB userService) {
		this.userService = userService;
	}

	/*
	 * private String elementId; private String type; private String name; private
	 * Boolean active; private Date createdTimestamp; private Creator createdBy;
	 * private Location location; private Map<String, Object> elementAttribute;
	 */
	@Override
	@Transactional
	public ElementBoundary create(String managerEmail, ElementBoundary element) {
		if(!this.validator.validateElementName(element)) {
			throw new InvalidElementName("Invalid element name");
		}
		
		if(!this.validator.validateElementType(element)) {
			throw new InvalidElementType("Invalid element type");
		}
		
		LastElementIdValue elementId = this.lastValueDao.save(new LastElementIdValue());
		ElementEntity newElementEntity = this.entityConverter.convertToEntity(element);
		newElementEntity.setCreatedBy(new Creator(managerEmail));
		newElementEntity.setElementId(elementId.getLastIdValue());
		newElementEntity.setCreatedTimestamp(new Date());

		this.lastValueDao.delete(elementId);
		newElementEntity = this.elementDao.save(newElementEntity);
		return this.entityConverter.convertFromEntity(newElementEntity);
	}

	@Override
	@Transactional
	public ElementBoundary update(String managerEmail, String elementId, ElementBoundary update) {
		ElementBoundary existing = this.getSpecificElement(managerEmail, elementId);
		// Note there are 3 attributes that not gets updated (elemendId,Date, Creator)
		if (this.validator.validateElementType(update)) {
			existing.setType(update.getType());
		}

		if (this.validator.validateElementName(update)) {
			existing.setName(update.getName());
		}

		if (this.validator.validateElementActive(update) && update.getActive() != existing.getActive()) {
			existing.setActive(update.getActive());
		}

		if (this.validator.validateElementLocation(update)) {
			existing.setLocation(update.getLocation());
		}
		if (this.validator.validateElementAttr(update)) {
			existing.setElementAttribute(update.getElementAttribute());
		}

		this.elementDao.save(this.entityConverter.convertToEntity(existing));

		return existing;
	}

	@Override
	@Transactional(readOnly = true)
	public ElementBoundary getSpecificElement(String userEmail, String elementId) {
		Optional<ElementEntity> entity = this.elementDao.findById(this.entityConverter.toEntityId(elementId));

		if (entity.isPresent()) {
			return this.entityConverter.convertFromEntity(entity.get());
		} else {
			throw new ElementNotFoundException("Could not find element for id: " + elementId);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAll(String userEmail) {

		List<ElementBoundary> rv = new ArrayList<>();

		Iterable<ElementEntity> content = this.elementDao.findAll();

		for (ElementEntity element : content) {
			rv.add(this.entityConverter.convertFromEntity(element));
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
	public void bindChildToParent(String managerEmail, String parentElementId, ElementIdBoundary input) {
		ElementEntity parent = this.elementDao.findById(this.entityConverter.toEntityId(parentElementId))
				.orElseThrow(() -> new ElementNotFoundException("Could not find element for id: " + parentElementId));

		ElementEntity child = this.elementDao.findById(this.entityConverter.toEntityId(input.getId()))
				.orElseThrow(() -> new ElementNotFoundException("Could not find element for id: " + parentElementId));

		parent.bindChildAndParent(child);
		this.elementDao.save(parent);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ElementBoundary> getAllElementChildrens(String userEmail, String parentElementId) {
		ElementEntity parent = this.elementDao.findById(this.entityConverter.toEntityId(parentElementId))
				.orElseThrow(() -> new ElementNotFoundException("Could not find element for id: " + parentElementId));
		
		return parent.getChildrens().stream().map(this.entityConverter::convertFromEntity).collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<ElementBoundary> getAllElementParents(String userEmail, String childElementId) {
		ElementEntity child = this.elementDao.findById(this.entityConverter.toEntityId(childElementId))
				.orElseThrow(() -> new ElementNotFoundException("Could not find element for id: " + childElementId));
		
		return  child.getParents().stream().map(this.entityConverter::convertFromEntity).collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> searchElementsByName(String userEmail, String name, int size, int page) {
		UserBoundary user = this.userService.login(userEmail);
		// If role is player return only elements with ACTIVE = TRUE
		if (this.validator.isPlayer(user)) {
			return this.elementDao.findAllByActiveAndNameLike(
					true, name, PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		}
		
		// If role is admin / manager
		if (this.validator.isAdmin(user) || this.validator.isManager(user))
		return this.elementDao.findAllByNameLike(
				name, PageRequest.of(page, size, Direction.ASC, "elementId"))
		.stream()
		.map(this.entityConverter::convertFromEntity)
		.collect(Collectors.toList());
		
		// If for unknown reason there is other role to the user return empty list
		return new ArrayList<ElementBoundary>();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> searchElementsByType(String userEmail, String type, int size, int page) {
		UserBoundary user = this.userService.login(userEmail);
		// If role is player return only elements with ACTIVE = TRUE
		if (this.validator.isPlayer(user)) {
			return this.elementDao.findAllByActiveAndType(
					true, type, PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		}
		
		// If role is admin / manager
		if (this.validator.isAdmin(user) || this.validator.isManager(user))
		return this.elementDao.findAllByType(
				type, PageRequest.of(page, size, Direction.ASC, "elementId"))
		.stream()
		.map(this.entityConverter::convertFromEntity)
		.collect(Collectors.toList());
		
		// If for an unknown reason the user has other role, return empty list.
		return new ArrayList<ElementBoundary>();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAllElements(String userEmail, int size, int page) {
		UserBoundary user = this.userService.login(userEmail);
		// If role is player return only elements with ACTIVE = TRUE
		if (this.validator.isPlayer(user)) {
			return this.elementDao.findAllByActive(
					true, PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		}
		
		// If role is Admin / Manager
		if (this.validator.isAdmin(user) || this.validator.isManager(user))
		return this.elementDao.findAll(
				PageRequest.of(page, size, Direction.ASC, "elementId"))
		.stream()
		.map(this.entityConverter::convertFromEntity)
		.collect(Collectors.toList());
		
		// If for an unknown reason the user has other role, return empty list.
		return new ArrayList<ElementBoundary>();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAllElementChildrens(String userEmail, String parentElementId, int size, int page) {
		UserBoundary parent = this.userService.login(userEmail);
		Optional<ElementEntity> parentElement = this.elementDao.findById(this.entityConverter.toEntityId(parentElementId));
		// If role is player return only elements with ACTIVE = TRUE
		if (this.validator.isPlayer(parent)) {
			return this.elementDao.findAllByActiveAndParents(
					true,
					parentElement.get(),
					PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		}
		
		// If role is Admin / Manager
		if (this.validator.isAdmin(parent) || this.validator.isManager(parent))
		return this.elementDao.findAllByParents(
				parentElement.get(),
				PageRequest.of(page, size, Direction.ASC, "elementId"))
		.stream()
		.map(this.entityConverter::convertFromEntity)
		.collect(Collectors.toList());
		
		// If for an unknown reason the user has other role, return empty list.
		return new ArrayList<ElementBoundary>();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAllElementParents(String userEmail, String childElementId, int size, int page) {
		UserBoundary child = this.userService.login(userEmail);
		Optional<ElementEntity> childElement = this.elementDao.findById(this.entityConverter.toEntityId(childElementId));
		// If role is player return only elements with ACTIVE = TRUE
		if (this.validator.isPlayer(child)) {
			return this.elementDao.findAllByActiveAndChildrens(
					true,
					childElement.get(),
					PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		}
		
		// If role is Admin / Manager
		if (this.validator.isAdmin(child) || this.validator.isManager(child))
		return this.elementDao.findAllByChildrens(
				childElement.get(),
				PageRequest.of(page, size, Direction.ASC, "elementId"))
		.stream()
		.map(this.entityConverter::convertFromEntity)
		.collect(Collectors.toList());
		
		// If for an unknown reason the user has other role, return empty list.
		return new ArrayList<ElementBoundary>();
	}
}
