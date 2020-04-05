package acs.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ElementBoundary;
import acs.boundaries.UserBoundary;
import acs.data.Creator;
import acs.data.UserRole;

@RestController
public class UserController {

	@RequestMapping(path = "/acs/users/login/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary getUserBoundary(@PathVariable("userEmail") String userEmail) {
		// Manager - new UserBoundary("Miri@gmail.com", UserRole.MANAGER, "Miri", ";)")
		// Player - new UserBoundary("Noar@gmail.com", UserRole.ADMIN, "Naori", ";)")
		// ADMIN - new UserBoundary("Gil@gmail.com", UserRole.PLAYER, "Gil", ";)")
		return new UserBoundary("Miri@gmail.com", UserRole.MANAGER, "Miri", ";)");
	}

	@RequestMapping(path = "/acs/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary createUserBoundary(@RequestBody UserBoundary input) {
		//// ---- save to data base in the future ----- //////
		return new UserBoundary("Miri@gmail.com", UserRole.MANAGER, "Miri", ";)");
	}

	@RequestMapping(path = "/acs/users/{userEmail}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@RequestBody UserBoundary input, @PathVariable("userEmail") String userEmail) {
		System.err.println(input);
	}

}
