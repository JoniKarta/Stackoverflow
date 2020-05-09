package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidUsernameException extends RuntimeException {

	private static final long serialVersionUID = -567355903517223789L;

	public InvalidUsernameException() {
		super();
	}

	public InvalidUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUsernameException(String message) {
		super(message);
	}

	public InvalidUsernameException(Throwable cause) {
		super(cause);
	}
}