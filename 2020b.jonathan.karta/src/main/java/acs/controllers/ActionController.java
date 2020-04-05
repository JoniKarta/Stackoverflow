package acs.controllers;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ActionBoundary;

@RestController
public class ActionController {

	@RequestMapping(path = "/acs/actions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object invokeAction(@RequestBody ActionBoundary input) {
		input.setCreatedTimestamp(new Date());
		// input.setCreatedBy(new Creator(managerEmail));
		return input;
	}

}
