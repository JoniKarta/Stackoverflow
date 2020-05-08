package acs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import acs.boundaries.UserBoundary;
import acs.boundaries.UserRole;
import acs.dal.UserDao;
import acs.data.UserConverter;
import acs.data.UserEntity;

@Service
public class UserServiceWithDB implements UserService {
	private UserDao userDao;
	private UserConverter userConverter;

	@Autowired
	public UserServiceWithDB(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

//	@PostConstruct
//	public void init() {
//		this.database = Collections.synchronizedMap(new TreeMap<>());
//	}

	@Override
	@Transactional
	public UserBoundary createUser(UserBoundary user) {
		UserEntity newUser = this.userConverter.toEntity(user);
		newUser.setUserName(user.getUserName());
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
		// UserBoundary userB = this.login(userEmail);
		UserBoundary existing = this.login(userEmail);

		if (update.getAvatar() != null) {
			existing.setAvatar(update.getAvatar());
		}

		/** Unchangeable - email is the current DB key */
		/*
		 * if (update.getEmail() != null) { dirty = true; }
		 */

		if (update.getRole() != null && update.getRole() == UserRole.ADMIN && update.getRole() == UserRole.PLAYER
				&& update.getRole() == UserRole.MANAGER) {
			existing.setRole(update.getRole());
		}

		if (update.getUserName() != null) {
			existing.setUserName(update.getUserName());
		}
		this.userDao.save(this.userConverter.toEntity(existing));

		return existing;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(String adminEmail) {
		List<UserBoundary> rv = new ArrayList<>();

		Iterable<UserEntity> content = this.userDao.findAll();

		for (UserEntity user : content) {
			rv.add(this.userConverter.fromEntity(user));
		}
		return rv;
	}

	@Override
	@Transactional
	public void deleteAllUsers(String adminEmail) {
		this.userDao.deleteAll();
	}
}
