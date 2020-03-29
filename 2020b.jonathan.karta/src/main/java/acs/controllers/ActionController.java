package acs.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ActionBoundary;

@RestController
public class ActionController {

	@RequestMapping(path = "/acs/admin/actions/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ActionBoundary> getAllActions(@PathVariable("adminEmail") String adminEmail) {
		// check if adminEmail is admin.
		List<ActionBoundary> actionBoundaryList = new ArrayList<>();
		// read all details from DB to list.
		return actionBoundaryList;
	}
}
