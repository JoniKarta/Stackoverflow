package acs.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ElementBoundary;
import acs.data.Creator;
import acs.data.Location;

@RestController
public class ElementController {

	@RequestMapping(path = "/acs/elements/{userEmail}/{elementId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary getElementBoundary(@PathVariable("userEmail") String userEmail, @PathVariable("elementId") String elementId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nisim", "what the hell is your attribute");
		return new ElementBoundary("1a8c4hred","BOTTLE RECYCLE","Recycle bottle",true,new Location(32.11563, 34.51986),new Date(),new Creator("jonathan@gmail.com"),map);
	}
	
	@RequestMapping(path = "/acs/elements/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary[] getAllElements(@PathVariable("userEmail") String userEmail) {
		ElementBoundary e1 = new ElementBoundary("1a", "RECYCLE", "recycle bin", true, new Location(32.115634, 34.51986), new Date(), new Creator("Miri@gmail.com"), new HashMap<String ,Object>());
		ElementBoundary e2 = new ElementBoundary("1b", "BOTTLE", "bottle bin", true, new Location(32.115634, 34.51986),new Date(), new Creator("Gil@gmail.com"), new HashMap<String ,Object>());
		ElementBoundary e3 = new ElementBoundary("1c", "GLASS", "glass bin", false, new Location(32.115634, 34.51986), new Date(), new Creator("Miri@gmail.com"), new HashMap<String ,Object>());
		ElementBoundary e4 = new ElementBoundary("1d", "BOTTLE", "bottle bin", true, new Location(32.115634, 34.51986), new Date(), new Creator("Gil@gmail.com"), new HashMap<String ,Object>());
		ElementBoundary e5 = new ElementBoundary("1e", "PAPAER", "paper bin", false, new Location(32.115634, 34.51986), new Date(), new Creator("Miri@gmail.com"), new HashMap<String ,Object>());
		ElementBoundary[] elements = {e1,e2,e3,e4,e5};
		return elements;
	}
	
	@RequestMapping(path = "/acs/elements/{managerEmail}", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary createElementBoundary(@RequestBody ElementBoundary input, @PathVariable("managerEmail") String managerEmail) {
		input.setCreatedTimestamp(new Date());
		input.setCreatedBy(new Creator(managerEmail));
		return input;
	}

	@RequestMapping(path = "/acs/elements/{managerEmail}/{elementId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateElement(@PathVariable("managerEmail")String managerEmail,
			@PathVariable("elementId") String elementId,
			@RequestBody ElementBoundary update) {

	}

	@RequestMapping(path = "/acs/admin/elements/{adminEmail}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAllElements(@PathVariable("adminEmail") String adminEmail) {
		boolean isDatabaseEmpty = true;

		if (isDatabaseEmpty)
			return new ResponseEntity<>("No actions found in database", HttpStatus.NOT_FOUND);

		// elementService.deleteAll(); // delete elements from database
		return new ResponseEntity<>("Deleted all elements.", HttpStatus.OK);

	}
}
