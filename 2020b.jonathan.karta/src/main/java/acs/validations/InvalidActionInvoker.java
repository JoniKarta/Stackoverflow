package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidActionInvoker extends RuntimeException {

	private static final long serialVersionUID = 7713882625287442601L;

	public InvalidActionInvoker() {
		super();
	}

	public InvalidActionInvoker(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidActionInvoker(String message) {
		super(message);
	}

	public InvalidActionInvoker(Throwable cause) {
		super(cause);
	}
}