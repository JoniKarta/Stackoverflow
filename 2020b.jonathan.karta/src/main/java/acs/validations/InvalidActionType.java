package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidActionType extends RuntimeException {

	private static final long serialVersionUID = -2057259475405047941L;

	public InvalidActionType() {
		super();
	}

	public InvalidActionType(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidActionType(String message) {
		super(message);
	}

	public InvalidActionType(Throwable cause) {
		super(cause);
	}
}