package acs.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ActionBoundary;
import acs.logic.services.ActionService;

@RestController
public class ActionController {
	private ActionService actionService;
	
	@Autowired
	public ActionController(ActionService actionService) {
		super();
		this.actionService = actionService;
	}

	@RequestMapping(path = "/acs/actions",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object invokeAction(@RequestBody ActionBoundary input) {
		input.setCreatedTimestamp(new Date());
		return actionService.invokeAction(input);
	}

}
