package acs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ElementBoundary;
import acs.logic.ElementService;

@RestController
public class ElementController {

	private ElementService elementService;

	
	@Autowired
	public void setElementService(ElementService elementService) {
		this.elementService = elementService;
	}
	@RequestMapping(path = "/acs/elements/{managerEmail}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary createElementBoundary(@RequestBody ElementBoundary input,
			@PathVariable("managerEmail") String managerEmail) {
		return this.elementService.createNewElement(managerEmail, input);
	}

	@RequestMapping(path = "/acs/elements/{managerEmail}/{elementId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateElement(@PathVariable("managerEmail") String managerEmail,
			@PathVariable("elementId") String elementId, @RequestBody ElementBoundary update) {
		this.elementService.updateElement(managerEmail, elementId, update);
	}

	@RequestMapping(path = "/acs/elements/{userEmail}/{elementId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary getElementBoundary(@PathVariable("userEmail") String userEmail,
			@PathVariable("elementId") String elementId) {
		return this.elementService.getSpecificElement(userEmail, elementId);
	}

	@RequestMapping(path = "/acs/elements/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] getAllElements(@PathVariable("userEmail") String userEmail) {
		return this.elementService.getAllElements(userEmail).toArray(new ElementBoundary[0]);
	}

}
