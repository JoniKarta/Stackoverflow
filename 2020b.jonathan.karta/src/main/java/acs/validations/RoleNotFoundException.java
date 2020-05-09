package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 4629037210328618397L;

	public RoleNotFoundException() {
		super();
	}

	public RoleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoleNotFoundException(String message) {
		super(message);
	}

	public RoleNotFoundException(Throwable cause) {
		super(cause);
	}
}
