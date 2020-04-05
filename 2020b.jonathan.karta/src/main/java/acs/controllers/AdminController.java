package acs.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ActionBoundary;
import acs.boundaries.UserBoundary;
import acs.data.Element;
import acs.data.Invoker;
import acs.data.UserRole;

@RestController
public class AdminController {

	// Delete All Users
	@RequestMapping(path = "/acs/admin/users/{adminEmail}", method = RequestMethod.DELETE)
	public /* ResponseEntity<String> */ void deleteAllUsers(@PathVariable("adminEmail") String adminEmail) {
		/*
		 * boolean isDatabaseEmpty = false; // imitate a full DB scenario
		 * 
		 * if (isDatabaseEmpty) return new
		 * ResponseEntity<>("No actions found in database", HttpStatus.NOT_FOUND);
		 * 
		 * // elementService.deleteAll(); // delete elements from database return new
		 * ResponseEntity<>("Deleted all elements.", HttpStatus.OK);
		 */
	}

	// Delete All Elements
	@RequestMapping(path = "/acs/admin/elements/{adminEmail}", method = RequestMethod.DELETE)
	public /* ResponseEntity<String> */ void deleteAllElements(@PathVariable("adminEmail") String adminEmail) {
		/*
		 * boolean isDatabaseEmpty = false; // imitate a full DB scenario
		 * 
		 * if (isDatabaseEmpty) return new
		 * ResponseEntity<>("No actions found in database", HttpStatus.NOT_FOUND);
		 * 
		 * // elementService.deleteAll(); // delete elements from database return new
		 * ResponseEntity<>("Deleted all elements.", HttpStatus.OK);
		 */
	}

	// Delete All Actions
	@RequestMapping(path = "/acs/admin/actions/{adminEmail}", method = RequestMethod.DELETE)
	public /* ResponseEntity<String> */ void deleteAllActions(@PathVariable("adminEmail") String adminEmail) {
		/*
		 * boolean isDatabaseEmpty = false; // imitate a full DB scenario
		 * 
		 * if (isDatabaseEmpty) return new
		 * ResponseEntity<>("No actions found in database", HttpStatus.NOT_FOUND);
		 * 
		 * // actionsService.deleteAll(); // delete actions from database return new
		 * ResponseEntity<>("Deleted all actions.", HttpStatus.OK);
		 */
	}

	// Get All User Boundary
	@RequestMapping(path = "/acs/admin/users/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getAllUserBoundary(@PathVariable("adminEmail") String adminEmail) {
		UserBoundary[] userBoundary = { new UserBoundary("Jonathan@gmail.com", UserRole.PLAYER, "Joni", ":)"),
				new UserBoundary("miri@gmail.com", UserRole.PLAYER, "Miri", ";)"),
				new UserBoundary("naor@gmail.com", UserRole.PLAYER, "Noar", "0)"),
				new UserBoundary("gil@gmail.com", UserRole.PLAYER, "Gil", "&)"),
				new UserBoundary("dani@gmail.com", UserRole.PLAYER, "Dani", "$)") };
		return userBoundary;
	}

	// Get All Actions
	@RequestMapping(path = "/acs/admin/actions/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActionBoundary[] getAllActions(@PathVariable("adminEmail") String adminEmail) {
		Map<String, Object> actionAttr = new HashMap<>();
		actionAttr.put("key1", "don't know what to write");
		actionAttr.put("key2", "think again, still don't know what to write");
		actionAttr.put("key3", "need a break from this actions attributes");
		actionAttr.put("key4", 12.443);
		ActionBoundary[] actionList = {
				new ActionBoundary("3232", "type1", new Element("23"), new Date(), new Invoker("nisim@sl.com"),
						actionAttr),

				new ActionBoundary("4545", "type2", new Element("25"), new Date(), new Invoker("admin@s.afeka.ac.il"),
						actionAttr) };
		return actionList;
	}

}
