package acs.controllers;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ActionBoundary;
import acs.boundaries.Element;
import acs.boundaries.Invoker;

@RestController
public class ActionController {

	@RequestMapping(path = "/acs/admin/actions/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActionBoundary[] getAllActions(@PathVariable("adminEmail") String adminEmail) {
		// check if adminEmail is admin.
		ActionBoundary[] list = {
				new ActionBoundary("3232", "type1", new Element("23"), new Date(), new Invoker("nisim@sl.com"), null),
				new ActionBoundary() };
		// read all details from DB to list.
		return list;
	}
}
