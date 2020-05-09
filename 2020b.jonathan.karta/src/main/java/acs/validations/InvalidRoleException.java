package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidRoleException extends RuntimeException {

	private static final long serialVersionUID = 2084331712536606124L;

	public InvalidRoleException() {
		super();
	}

	public InvalidRoleException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRoleException(String message) {
		super(message);
	}

	public InvalidRoleException(Throwable cause) {
		super(cause);
	}
}
