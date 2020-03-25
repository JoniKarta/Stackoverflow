package acs;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	private String greeting = "Goodbye";//"Hi";
	private String suffix = ".";//"!";
	
	@RequestMapping(path = "/hello/{name}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageBoundary hello (@PathVariable("name") String name) {
		return new MessageBoundary("Hello " + name + "!");
	}
	
	@RequestMapping(path = "/greet/{name}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageBoundary greet (@PathVariable("name") String name) {
		return new MessageBoundary(greeting + " " + name + this.suffix);
	}
	
}
