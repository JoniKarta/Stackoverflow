package acs.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.UserBoundary;
import acs.logic.services.UserService;

@CrossOrigin
@RestController
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService usersService) {
		this.userService = usersService;
	}

	@RequestMapping(path = "/acs/users/login/{userEmail}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary getUserBoundary(@PathVariable("userEmail") String userEmail) {	
		return this.userService.login(userEmail);
	}

	@RequestMapping(path = "/acs/users", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary createUserBoundary(
		 @RequestBody UserBoundary input) {
		
		return this.userService.createUser(input);
	}

	@RequestMapping(path = "/acs/users/{userEmail}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary updateUser(@RequestBody UserBoundary input,
			@PathVariable("userEmail") String userEmail) {
		return this.userService.updateUser(userEmail, input);
	}
}
