package acs.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ActionBoundary;
import acs.boundaries.ElementBoundary;
import acs.data.Creator;
import acs.data.Element;
import acs.data.Invoker;

@RestController
public class ActionController {

	@RequestMapping(path = "/acs/admin/actions/{adminEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActionBoundary[] getAllActions(@PathVariable("adminEmail") String adminEmail) {
		Map<String,Object> actionAttr = new HashMap<>();
		actionAttr.put("key1", "don't know what to write");
		actionAttr.put("key2","think again, still don't know what to write");
		actionAttr.put("key3", "need a break from this actions attributes");
		actionAttr.put("key4", 12.443);
		ActionBoundary[] actionList = {
				new ActionBoundary("3232", "type1", 
				new Element("23"), new Date(), 
				new Invoker("nisim@sl.com"), actionAttr),
				
				new ActionBoundary("4545", "type2", 
				new Element("25"), new Date(), 
				new Invoker("admin@s.afeka.ac.il"), actionAttr)};
		return actionList;
	}
	
	@RequestMapping(path = "/acs/actions", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object createActionBoundary(@RequestBody ActionBoundary input) {
		input.setCreatedTimestamp(new Date());
		//input.setCreatedBy(new Creator(managerEmail));
		return input;
	}
	
}
