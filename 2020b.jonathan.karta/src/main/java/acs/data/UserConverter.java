package acs.data;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import acs.boundaries.UserBoundary;
import acs.boundaries.UserRole;

@Component
public class UserConverter {

	@PostConstruct
	public void setup() {

	}

	public UserBoundary fromEntity(UserEntity user) {

		try {
			return new UserBoundary(
				user.getEmail(),
				user.getRole() != null ? UserRole.valueOf(user.getRole().name().toUpperCase()) : null,
				user.getUserName(),
				user.getAvatar());
		} catch (Exception e) {
			throw new RuntimeException("could not convert UserEntity to UserBoundary! "+user.toString());
		}
	}

	public UserEntity toEntity (UserBoundary user) {
		try {
			return new UserEntity(
				user.getEmail(),
				user.getRole() != null ? UserRoleEntity.valueOf(user.getRole().name().toUpperCase()) : null,
				user.getUsername(),
				user.getAvatar().toString());
		} catch (Exception e) {
			throw new RuntimeException("could not convert UserBoundary to UserEntity! "+user.toString());
		}
	}
}