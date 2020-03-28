package acs.controllers;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acs.boundaries.ElementBoundary;
import acs.data.ElementAttr;
import acs.data.ElementType;
import acs.data.Location;
import acs.data.UserGenerator;
import acs.data.UserRole;

@RestController
public class ElementController {

	@RequestMapping(path = "/acs/elements/{userEmail}/{elementId}",
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ElementBoundary getElementBoundary(@PathVariable("userEmail") String userEmail,
			@PathVariable("elementId") String elementId) {
		return new ElementBoundary("1a8c4hred", ElementType.BOTTLE_RECYCLE,
				ElementType.BOTTLE_RECYCLE.getName(), true,
				new Location(32.115634, 34.519865),new Date(), 
				new UserGenerator("jonathan@gmail.com"), new ElementAttr());
	}

}
