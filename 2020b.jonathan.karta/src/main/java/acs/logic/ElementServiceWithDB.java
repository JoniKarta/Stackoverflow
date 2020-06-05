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
import acs.dal.ElementDao;
import acs.dal.LastElementIdValue;
import acs.dal.LastElementValueDao;
import acs.dal.UserDao;
import acs.boundaries.data.Creator;
import acs.data.ElementConverter;
import acs.data.ElementEntity;
import acs.data.UserEntity;
import acs.logic.services.EnhancedElementService;
import acs.validations.ElementNotFoundException;
import acs.validations.InvalidElementName;
import acs.validations.InvalidElementType;
import acs.validations.InvalidRoleException;
import acs.validations.UserNotFoundException;
import acs.validations.Validator;

@Service
public class ElementServiceWithDB implements EnhancedElementService {
	private ElementConverter entityConverter;
	private ElementDao elementDao;
	private LastElementValueDao lastValueDao;
	private Validator validator;
	private UserDao userDao;

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
	public void setUserService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public ElementBoundary create(String managerEmail, ElementBoundary element) {
		Optional<UserEntity> user = this.userDao.findById(managerEmail);
		if (user.isPresent()) {
			if (this.validator.isManager(user.get())) {
				if (!this.validator.validateElementName(element)) {
					throw new InvalidElementName("Invalid element name");
				}

				if (!this.validator.validateElementType(element)) {
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
			} else {
				throw new InvalidRoleException("Unauthorized user");
			}
		} else {
			throw new UserNotFoundException("User with email not found: " + managerEmail);
		}
	}

	@Override
	@Transactional
	public ElementBoundary update(String managerEmail, String elementId, ElementBoundary update) {
		Optional<UserEntity> user = this.userDao.findById(managerEmail);
		if (user.isPresent() && (this.validator.isManager(user.get()))) {
			ElementBoundary existing = this.getSpecificElement(managerEmail, elementId);
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
		} else {
			throw new InvalidRoleException("Unauthorized user");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ElementBoundary getSpecificElement(String userEmail, String elementId) {
		Optional<ElementEntity> entity = this.elementDao.findById(this.entityConverter.toEntityId(elementId));
		Optional<UserEntity> user = this.userDao.findById(userEmail);
		if (user.isPresent()) {
			if (entity.isPresent()) {
				if (this.validator.isManager(user.get()) || this.validator.isAdmin(user.get())) {
					return this.entityConverter.convertFromEntity(entity.get());
				} else if (this.validator.isActive(entity.get()) && this.validator.isPlayer(user.get())) {
					return this.entityConverter.convertFromEntity(entity.get());
				} else {
					throw new InvalidRoleException("Unauthorized user");
				}
			} else {
				throw new ElementNotFoundException("Could not find element for id: " + elementId);
			}

		} else {
			throw new UserNotFoundException("Invalid user");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAll(String userEmail) {
		Optional<UserEntity> user = this.userDao.findById(userEmail);
		List<ElementBoundary> rv = new ArrayList<>();
		Iterable<ElementEntity> content = null;
		if (user.isPresent()) {
			if (this.validator.isManager(user.get()))
				content = this.elementDao.findAll();
			else
				content = this.elementDao.findAllByActive(false);
		} else {
			throw new UserNotFoundException("Invalid user");
		}
		for (ElementEntity element : content) {
			rv.add(this.entityConverter.convertFromEntity(element));
		}
		return rv;
	}

	@Override
	@Transactional
	public void deleteAllElements(String adminEmail) {
		Optional<UserEntity> user = this.userDao.findById(adminEmail);
		if (user.isPresent() && this.validator.isAdmin(user.get()))
			this.elementDao.deleteAll();
		else
			throw new InvalidRoleException("Unauthorized user");
	}

	@Override
	@Transactional
	public void bindChildToParent(String managerEmail, String parentElementId, ElementIdBoundary input) {
		Optional<UserEntity> user = this.userDao.findById(managerEmail);
		if (user.isPresent() && (this.validator.isManager(user.get()))) {
			ElementEntity parent = this.elementDao.findById(this.entityConverter.toEntityId(parentElementId))
					.orElseThrow(
							() -> new ElementNotFoundException("Could not find element for id: " + parentElementId));

			ElementEntity child = this.elementDao.findById(this.entityConverter.toEntityId(input.getId())).orElseThrow(
					() -> new ElementNotFoundException("Could not find element for id: " + parentElementId));

			parent.bindChildAndParent(child);
			this.elementDao.save(parent);
		} else
			throw new InvalidRoleException("Unauthorized user");
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ElementBoundary> getAllElementChildrens(String userEmail, String parentElementId) {
		ElementEntity parent = this.elementDao.findById(this.entityConverter.toEntityId(parentElementId))
				.orElseThrow(() -> new ElementNotFoundException("Could not find element for id: " + parentElementId));
		
		return parent.getChildrens()
				.stream()
				.map(this.entityConverter::convertFromEntity)
				.collect(Collectors.toSet());
	}


	@Override
	@Transactional(readOnly = true)
	public Collection<ElementBoundary> getAllElementParents(String userEmail, String childElementId) {
		ElementEntity child = this.elementDao.findById(this.entityConverter.toEntityId(childElementId))
				.orElseThrow(() -> new ElementNotFoundException("Could not find element for id: " + childElementId));
		
		return  child.getParents()
				.stream()
				.map(this.entityConverter::convertFromEntity)
				.collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> searchElementsByName(String userEmail, String name, int size, int page) {
		Optional<UserEntity> user = this.userDao.findById(userEmail);
		if (user.isPresent()) {
			if (this.validator.isPlayer(user.get())) {
				return this.elementDao
						.findAllByActiveAndNameLike(true, name, PageRequest.of(page, size, Direction.ASC, "elementId"))
				.stream()
				.map(this.entityConverter::convertFromEntity)
				.collect(Collectors.toList());
			}


			if (this.validator.isManager(user.get()))
			return this.elementDao.findAllByNameLike(
					name, PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		} else throw new UserNotFoundException("Invalid user");
		return new ArrayList<ElementBoundary>();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> searchElementsByType(String userEmail, String type, int size, int page) {
		Optional<UserEntity> user = this.userDao.findById(userEmail);
		if(user.isPresent()) {
			if (this.validator.isPlayer(user.get())) {
				return this.elementDao.findAllByActiveAndTypeLike(
						true, type, PageRequest.of(page, size, Direction.ASC, "elementId"))
				.stream()
				.map(this.entityConverter::convertFromEntity)
				.collect(Collectors.toList());
			}
			
			if (this.validator.isManager(user.get()))
			return this.elementDao.findAllByTypeLike(
					type, PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		} else throw new UserNotFoundException("Invalid user");
		return new ArrayList<ElementBoundary>();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAllElements(String userEmail, int size, int page) {
		Optional<UserEntity> user = this.userDao.findById(userEmail);
		if(user.isPresent()) {
			if (this.validator.isPlayer(user.get())) {
				return this.elementDao.findAllByActive(
						true, PageRequest.of(page, size, Direction.ASC, "elementId"))
				.stream()
				.map(this.entityConverter::convertFromEntity)
				.collect(Collectors.toList());
			}
			
			if (this.validator.isManager(user.get()))
			return this.elementDao.findAll(
					PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		} else throw new UserNotFoundException("Invalid user");
		return new ArrayList<ElementBoundary>();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAllElementChildrens(String userEmail, String parentElementId, int size, int page) {
		Optional<UserEntity> parent = this.userDao.findById(userEmail);
		Optional<ElementEntity> parentElement = this.elementDao.findById(this.entityConverter.toEntityId(parentElementId));
		
		if(parent.isPresent() && parentElement.isPresent()) {
			if (this.validator.isPlayer(parent.get())) {
				return this.elementDao.findAllByActiveAndParents(
						true,
						parentElement.get(),
						PageRequest.of(page, size, Direction.ASC, "elementId"))
				.stream()
				.map(this.entityConverter::convertFromEntity)
				.collect(Collectors.toList());
			}
			
			if (this.validator.isManager(parent.get()))
			return this.elementDao.findAllByParents(
					parentElement.get(),
					PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		} else throw new UserNotFoundException("Invalid user");
		return new ArrayList<ElementBoundary>();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ElementBoundary> getAllElementParents(String userEmail, String childElementId, int size, int page) {
		Optional<UserEntity> child = this.userDao.findById(userEmail);
		Optional<ElementEntity> childElement = this.elementDao.findById(this.entityConverter.toEntityId(childElementId));
		
		if(child.isPresent() && childElement.isPresent()) {
			if (this.validator.isPlayer(child.get())) {
				return this.elementDao.findAllByActiveAndChildrens(
						true,
						childElement.get(),
						PageRequest.of(page, size, Direction.ASC, "elementId"))
				.stream()
				.map(this.entityConverter::convertFromEntity)
				.collect(Collectors.toList());
			}
			
			if (this.validator.isManager(child.get()))
			return this.elementDao.findAllByChildrens(
					childElement.get(),
					PageRequest.of(page, size, Direction.ASC, "elementId"))
			.stream()
			.map(this.entityConverter::convertFromEntity)
			.collect(Collectors.toList());
		} else throw new UserNotFoundException("Invalid user");
		return new ArrayList<ElementBoundary>();
	}
}
