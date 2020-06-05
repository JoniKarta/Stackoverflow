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

import acs.boundaries.UserBoundary;
import acs.dal.UserDao;
import acs.data.UserConverter;
import acs.data.UserEntity;
import acs.logic.services.EnhancedUserService;
import acs.validations.ElementNotFoundException;
import acs.validations.InvalidAvatarException;
import acs.validations.InvalidEmailFormatException;
import acs.validations.InvalidRoleException;
import acs.validations.InvalidUsernameException;
import acs.validations.RoleNotFoundException;
import acs.validations.UserNotFoundException;
import acs.validations.Validator;

@Service
public class UserServiceWithDB implements EnhancedUserService {
	private UserDao userDao;
	private UserConverter userConverter;
	private Validator validator;

	@Autowired
	public UserServiceWithDB(UserDao userDao, Validator validator) {
		this.userDao = userDao;
		this.validator = validator;
	}

	@Autowired
	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	@Override
	@Transactional
	public UserBoundary createUser(UserBoundary user) {
		if(!this.validator.isRoleExist(user)) {
			throw new RoleNotFoundException("Invalid role");
		}
		
		if(!this.validator.validateUserEmail(user.getEmail())) {
			throw new InvalidEmailFormatException("Invalid email");
		}
		
		if(!this.validator.validateUsername(user)) {
			throw new InvalidUsernameException("Invalid username");
		}
		
		if(!this.validator.validateAvatar(user)) {
			throw new InvalidAvatarException("Invalid avatar");
		}
		
		UserEntity newUser = this.userConverter.toEntity(user);
		newUser.setCreation(new Date());
		newUser = this.userDao.save(newUser);

		return this.userConverter.fromEntity(newUser);
	}

	@Override
	@Transactional(readOnly = true)
	public UserBoundary login(String userEmail) {
		Optional<UserEntity> entity = this.userDao.findById(userEmail);

		if (entity.isPresent()) {
			return this.userConverter.fromEntity(entity.get());
		} else {
			throw new ElementNotFoundException("Could not find user for Email: " + userEmail);
		}
	}

	@Override
	@Transactional
	public UserBoundary updateUser(String userEmail, UserBoundary update) {
		UserBoundary existing = this.login(userEmail);

		if (this.validator.validateAvatar(update)) {
			existing.setAvatar(update.getAvatar());
		}

		if (this.validator.isRoleExist(update)) {
			existing.setRole(update.getRole());
		}

		if (this.validator.validateUsername(update)) {
			existing.setUserName(update.getUsername());
		}
		this.userDao.save(this.userConverter.toEntity(existing));

		return existing;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(String adminEmail) {
		UserEntity myUser = this.userDao.findById(adminEmail)
				.orElseThrow(() -> new UserNotFoundException("Could not found user: " + adminEmail));
		
		if (this.validator.isAdmin(myUser)) {
			List<UserBoundary> rv = new ArrayList<>();
			Iterable<UserEntity> content = this.userDao.findAll();
			for (UserEntity user : content) {
				rv.add(this.userConverter.fromEntity(user));
			}
			return rv;
		} else {
			throw new InvalidRoleException("Unauthorized user role");
		}
	}

	@Override
	@Transactional
	public void deleteAllUsers(String adminEmail) {
		UserEntity user = this.userDao.findById(adminEmail)
				.orElseThrow(() -> new UserNotFoundException("Could not found user: " + adminEmail));
		if (this.validator.isAdmin(user)) {
			this.userDao.deleteAll();
		} else {
			throw new InvalidRoleException("Unauthorized user role");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(String adminEmail, int size, int page) {
		UserEntity user = this.userDao.findById(adminEmail)
				.orElseThrow(() -> new UserNotFoundException("Could not found user: " + adminEmail));
		if (this.validator.isAdmin(user)) {
			return this.userDao.findAll(PageRequest.of(page, size, Direction.ASC, "email")).getContent().stream()
					.map(this.userConverter::fromEntity).collect(Collectors.toList());

		} else {
			throw new InvalidRoleException("Unauthorized user role");
		}
	}
}
