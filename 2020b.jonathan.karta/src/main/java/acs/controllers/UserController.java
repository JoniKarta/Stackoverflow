package acs.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.UserBoundary;
import acs.data.UserRole;

@RestController
public class UserController {

	@RequestMapping(path = "/acs/admin/users/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getAllUserBoundary(@PathVariable("adminEmail") String adminEmail) {
		UserBoundary[] userBoundary = {
				new UserBoundary("Jonathan@gmail.com", UserRole.PLAYER, "Joni", ":)"),
				new UserBoundary("miri@gmail.com", UserRole.PLAYER, "Miri", ";)"),
				new UserBoundary("naor@gmail.com", UserRole.PLAYER, "Noar", "0)"),
				new UserBoundary("gil@gmail.com", UserRole.PLAYER, "Gil", "&)"),
				new UserBoundary("dani@gmail.com", UserRole.PLAYER, "Dani", "$)")};
		return userBoundary;
	}

	@RequestMapping(path = "/acs/users/login/{userEmail}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary UserBoundary(@PathVariable("userEmail") String userEmail) {
		// Manager - new UserBoundary("Miri@gmail.com", UserRole.MANAGER, "Miri", ";)") 
		// Player - new UserBoundary("Noar@gmail.com", UserRole.ADMIN, "Naori", ";)")
		// ADMIN - new UserBoundary("Gil@gmail.com", UserRole.PLAYER, "Gil", ";)")
		return new UserBoundary("Miri@gmail.com", UserRole.MANAGER, "Miri", ";)") ;
	}

}
