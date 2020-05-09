package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidAvatarException extends RuntimeException {
	
static final long serialVersionUID = 6398539939108680773L;


	public InvalidAvatarException() {
		super();
	}

	public InvalidAvatarException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAvatarException(String message) {
		super(message);
	}

	public InvalidAvatarException(Throwable cause) {
		super(cause);
	}
}
