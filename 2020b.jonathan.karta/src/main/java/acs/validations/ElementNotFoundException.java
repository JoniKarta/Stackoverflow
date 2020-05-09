package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5889682267247086200L;

	public ElementNotFoundException() {
		super();
	}

	public ElementNotFoundException(String message) {
		super(message);
	}
}
