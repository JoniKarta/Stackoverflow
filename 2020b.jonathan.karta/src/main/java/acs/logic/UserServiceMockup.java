package acs.logic;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acs.boundaries.UserBoundary;
import acs.data.UserConverter;
import acs.data.UserEntity;

@Service
public class UserServiceMockup implements UserService {
	private Map<String, UserEntity> database;
	private UserConverter userConverter;

	public UserServiceMockup() {
		System.err.println("Spring initialized me");
	}

	@Autowired
	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	@PostConstruct
	public void init() {
		this.database = Collections.synchronizedMap(new TreeMap<>());
	}

	@Override
	public UserBoundary createUser(UserBoundary user) {
		UserEntity userEntity = this.userConverter.toEntity(user);
		userEntity.setCreation(new Date());

		this.database.put(user.getEmail(), userEntity);
		return this.userConverter.fromEntity(userEntity);
	}

	@Override
	public UserBoundary login(String userEmail) {
		UserEntity user = this.database.get(userEmail);

		if (user != null) {
			return this.userConverter.fromEntity(this.database.get(userEmail));
		} else {
			throw new UserNotFoundException("Could not find a user with email: " + userEmail);
		}
	}

	@Override
	public UserBoundary updateUser(String userEmail, UserBoundary update) {
		UserBoundary userB = this.login(userEmail);
		boolean dirty = false;

		if (update.getAvatar() != null) {
			 userB.setAvatar(update.getAvatar());
			dirty = true;
		}
		
		/** Unchangeable - email is the current DB key */
		/*
		 * if (update.getEmail() != null) { dirty = true; }
		 */

		if (update.getRole() != null) {
			dirty = true;
		}

		if (update.getRole() != null) {
			dirty = true;
		}

		if (update.getUserName() != null) {
			dirty = true;
		}

		if (dirty) {
			this.database.put(userEmail, this.userConverter.toEntity(userB));
		}
		return userB;
	}

	@Override
	public List<UserBoundary> getAllUsers(String adminEmail) {
		return this.database.values()
				.stream()
				.map(entity -> this.userConverter.fromEntity(entity))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAllUsers(String adminEmail) {
		this.database.clear();
	}
}
