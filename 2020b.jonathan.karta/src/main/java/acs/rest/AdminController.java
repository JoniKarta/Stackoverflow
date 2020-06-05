package acs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ActionBoundary;
import acs.boundaries.UserBoundary;
import acs.logic.services.ElementService;
import acs.logic.services.EnhancedActionService;
import acs.logic.services.EnhancedUserService;

@RestController
public class AdminController {
	private EnhancedActionService actionService;
	private EnhancedUserService userService;
	private ElementService elementService;
	
	@Autowired
	public void setActionService(EnhancedActionService actionService) {
		this.actionService = actionService;
	}
	
	@Autowired
	public void setUserService(EnhancedUserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setElementService(ElementService elementService) {
		this.elementService = elementService;
	}
	
	
	@RequestMapping(path = "/acs/admin/users/{adminEmail}", method = RequestMethod.DELETE)
	public void deleteAllUsers(@PathVariable("adminEmail") String adminEmail) {
		this.userService.deleteAllUsers(adminEmail);
	}

	@RequestMapping(path = "/acs/admin/elements/{adminEmail}", method = RequestMethod.DELETE)
	public void deleteAllElements(@PathVariable("adminEmail") String adminEmail) {
		this.elementService.deleteAllElements(adminEmail);
	}

	@RequestMapping(path = "/acs/admin/actions/{adminEmail}", method = RequestMethod.DELETE)
	public void deleteAllActions(@PathVariable("adminEmail") String adminEmail) {
		actionService.deleteAllActions(adminEmail);
	}

	@RequestMapping(path = "/acs/admin/users/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getAllUserBoundary(@PathVariable("adminEmail") String adminEmail,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page, 
			@RequestParam(name = "size", required = false, defaultValue = "10") int size) {
		return this.userService.getAllUsers(adminEmail,size,page).toArray(new UserBoundary[0]);
	}

	@RequestMapping(path = "/acs/admin/actions/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActionBoundary[] getAllActions(@PathVariable("adminEmail") String adminEmail,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page, 
			@RequestParam(name = "size", required = false, defaultValue = "10") int size) {
		return actionService.getAllActions(adminEmail,size,page).toArray(new ActionBoundary[0]);
	}

}
