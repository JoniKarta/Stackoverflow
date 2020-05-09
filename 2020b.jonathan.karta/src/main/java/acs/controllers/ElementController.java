package acs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ElementBoundary;
import acs.boundaries.ElementIdBoundary;
import acs.logic.ElementService;
import acs.logic.EnhancedElementService;

@RestController
public class ElementController {

	private EnhancedElementService elementService;

	@Autowired
	public void setElementService(EnhancedElementService elementService) {
		this.elementService = elementService;
	}

	@RequestMapping(path = "/acs/elements/{managerEmail}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary createElementBoundary(@RequestBody ElementBoundary input,
			@PathVariable("managerEmail") String managerEmail) {
		return this.elementService.create(managerEmail, input);
	}

	@RequestMapping(path = "/acs/elements/{managerEmail}/{elementId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateElement(@PathVariable("managerEmail") String managerEmail,
			@PathVariable("elementId") String elementId, @RequestBody ElementBoundary update) {
		this.elementService.update(managerEmail, elementId, update);
	}

	@RequestMapping(path = "/acs/elements/{userEmail}/{elementId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary getElementBoundary(@PathVariable("userEmail") String userEmail,
			@PathVariable("elementId") String elementId) {
		return this.elementService.getSpecificElement(userEmail, elementId);
	}

	@RequestMapping(path = "/acs/elements/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] getAllElements(@PathVariable("userEmail") String userEmail) {
		return this.elementService.getAll(userEmail).toArray(new ElementBoundary[0]);
	}

	@RequestMapping(path = "/acs/elements/{managerEmail}/{parentElementId}/children", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addChildToParent(@PathVariable("managerEmail") String managerEmail,
			@PathVariable("parentElementId") String parentElementId, @RequestBody ElementIdBoundary elementIdBoundary) {
		this.elementService.bindElements(managerEmail, parentElementId, elementIdBoundary);
	}
	
	@RequestMapping(path = "/acs/elements/{userEmail}/{parentElementId}/children", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] getAllElementChildren(@PathVariable("userEmail") String userEmail,
			@PathVariable("parentElementId") String parentElementId) {
		return this.elementService.getAllElementChildrens(userEmail, parentElementId).toArray(new ElementBoundary[0]);
	}
	
	@RequestMapping(path = "/acs/elements/{userEmail}/{childElementId}/parents")
	public ElementBoundary[] getAllElementParents(@PathVariable("userEmail") String userEmail,
			@PathVariable("childElementId") String childElementId){
		return this.elementService.getAllElementParents(userEmail, childElementId).toArray(new ElementBoundary[0]);

	}

}
