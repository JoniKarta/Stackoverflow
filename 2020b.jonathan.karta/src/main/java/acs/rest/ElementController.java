package acs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ElementBoundary;
import acs.boundaries.ElementIdBoundary;
import acs.logic.services.EnhancedElementService;

@RestController
public class ElementController {

	private EnhancedElementService elementService;

	@Autowired
	public void setElementService(EnhancedElementService elementService) {
		this.elementService = elementService;
	}

	@RequestMapping(path = "/acs/elements/{managerEmail}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary createElementBoundary(
			@RequestBody ElementBoundary input,
			@PathVariable("managerEmail") String managerEmail) {
		return this.elementService.create(managerEmail, input);
	}

	@RequestMapping(path = "/acs/elements/{managerEmail}/{elementId}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateElement(
			@PathVariable("managerEmail") String managerEmail,
			@PathVariable("elementId") String elementId, @RequestBody ElementBoundary update) {
		this.elementService.update(managerEmail, elementId, update);
	}

	@RequestMapping(path = "/acs/elements/{userEmail}/{elementId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary getElementBoundary(
			@PathVariable("userEmail") String userEmail,
			@PathVariable("elementId") String elementId) {
		return this.elementService.getSpecificElement(userEmail, elementId);
	}

	@RequestMapping(path = "/acs/elements/{userEmail}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] getAllElements(
			@PathVariable("userEmail") String userEmail,
			@RequestParam(name="size", required = false, defaultValue = "10") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {
		return this.elementService.getAllElements(userEmail, size, page)
				.toArray(new ElementBoundary[0]);
	}

	@RequestMapping(path = "/acs/elements/{managerEmail}/{parentElementId}/children",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addChildToParent(
			@PathVariable("managerEmail") String managerEmail,
			@PathVariable("parentElementId") String parentElementId,
			@RequestBody ElementIdBoundary elementIdBoundary) {
		this.elementService.bindChildToParent(managerEmail, parentElementId, elementIdBoundary);
	}
	
	@RequestMapping(path = "/acs/elements/{userEmail}/{parentElementId}/children",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] getAllElementChildren(
			@PathVariable("userEmail") String userEmail,
			@PathVariable("parentElementId") String parentElementId,
			@RequestParam(name="size", required = false, defaultValue = "10") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {
		return this.elementService.getAllElementChildrens(userEmail, parentElementId, size, page)
				.toArray(new ElementBoundary[0]);
	}
	
	@RequestMapping(path = "/acs/elements/{userEmail}/{childElementId}/parents",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] getAllElementParents(
			@PathVariable("userEmail") String userEmail,
			@PathVariable("childElementId") String childElementId,
			@RequestParam(name="size", required = false, defaultValue = "10") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page){
		return this.elementService.getAllElementParents(userEmail, childElementId, size, page)
				.toArray(new ElementBoundary[0]);
	}
	
	@RequestMapping(path = "/acs/elements/{userEmail}/search/byName/{name}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] searchElementsByName(
			@PathVariable("userEmail") String userEmail,
			@PathVariable("name") String name,
			@RequestParam(name="size", required = false, defaultValue = "10") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page){
		return this.elementService.searchElementsByName(userEmail, name, size, page)
				.toArray(new ElementBoundary[0]);
	}
	
	@RequestMapping(path = "/acs/elements/{userEmail}/search/byType/{type}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] searchElementsByType(
			@PathVariable("userEmail") String userEmail,
			@PathVariable("type") String type,
			@RequestParam(name="size", required = false, defaultValue = "10") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page){
		return this.elementService.searchElementsByType(userEmail, type, size, page)
				.toArray(new ElementBoundary[0]);
	}
}
