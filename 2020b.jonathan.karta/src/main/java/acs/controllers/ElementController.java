package acs.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ElementBoundary;
import acs.data.Creator;
import acs.data.Location;

@RestController
public class ElementController {

	@RequestMapping(path = "/acs/elements/{userEmail}/{elementId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary getElementBoundary(@PathVariable("userEmail") String userEmail,
			@PathVariable("elementId") String elementId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nisim", "gay");
		return new ElementBoundary(
				"1a8c4hred",
				"BOTTLE RECYCLE",
				"Recycle bottle",
				true,
				new Location(32.115634, 34.51986),
				new Date(),
				new Creator("jonathan@gmail.com"),
				map);
	}

}
