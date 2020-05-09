package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidEmailFormatException extends RuntimeException {

	private static final long serialVersionUID = 6934127484278630276L;

	public InvalidEmailFormatException() {
		super();
	}

	public InvalidEmailFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEmailFormatException(String message) {
		super(message);
	}

	public InvalidEmailFormatException(Throwable cause) {
		super(cause);
	}
}
