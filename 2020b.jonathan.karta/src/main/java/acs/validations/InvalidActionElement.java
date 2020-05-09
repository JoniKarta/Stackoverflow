package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidActionElement extends RuntimeException {

	private static final long serialVersionUID = -544079426677176890L;

	public InvalidActionElement() {
		super();
	}

	public InvalidActionElement(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidActionElement(String message) {
		super(message);
	}

	public InvalidActionElement(Throwable cause) {
		super(cause);
	}
}