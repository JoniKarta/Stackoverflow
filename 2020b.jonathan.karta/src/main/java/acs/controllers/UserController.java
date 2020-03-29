package acs.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.UserBoundary;

@RestController
public class UserController {

	@RequestMapping(path = "/acs/admin/users/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getAllUserBoundary(@PathVariable("adminEmail") String adminEmail) {
		// check if adminEmail is admin.
		
		// read all details from DB to list.
		return null;
	}

}
