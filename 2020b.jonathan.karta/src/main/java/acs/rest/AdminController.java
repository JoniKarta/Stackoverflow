package acs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ActionBoundary;
import acs.boundaries.UserBoundary;
import acs.logic.ActionService;
import acs.logic.ElementService;
import acs.logic.UserService;

@RestController
public class AdminController {
	private ActionService actionService;
	private UserService userService;
	private ElementService elementService;
	
	@Autowired
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setElementService(ElementService elementService) {
		this.elementService = elementService;
	}
	
	
	// Delete All Users
	@RequestMapping(path = "/acs/admin/users/{adminEmail}", method = RequestMethod.DELETE)
	public void deleteAllUsers(@PathVariable("adminEmail") String adminEmail) {
		this.userService.deleteAllUsers(adminEmail);
	}

	// Delete All Elements
	@RequestMapping(path = "/acs/admin/elements/{adminEmail}", method = RequestMethod.DELETE)
	public void deleteAllElements(@PathVariable("adminEmail") String adminEmail) {
		this.elementService.deleteAllElements(adminEmail);
	}

	// Delete All Actions
	@RequestMapping(path = "/acs/admin/actions/{adminEmail}", method = RequestMethod.DELETE)
	public void deleteAllActions(@PathVariable("adminEmail") String adminEmail) {
		actionService.deleteAllActions(adminEmail);
	}

	// Get All User Boundary
	@RequestMapping(path = "/acs/admin/users/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getAllUserBoundary(@PathVariable("adminEmail") String adminEmail) {
	
		return this.userService.getAllUsers(adminEmail).toArray(new UserBoundary[0]);
	}

	// Get All Actions
	@RequestMapping(path = "/acs/admin/actions/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActionBoundary[] getAllActions(@PathVariable("adminEmail") String adminEmail) {
		return actionService.getAllActions(adminEmail).toArray(new ActionBoundary[0]);
	}

}
