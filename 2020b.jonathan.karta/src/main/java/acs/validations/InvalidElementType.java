package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidElementType extends RuntimeException {

	private static final long serialVersionUID = -3015014951272535216L;

	public InvalidElementType() {
		super();
	}

	public InvalidElementType(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidElementType(String message) {
		super(message);
	}

	public InvalidElementType(Throwable cause) {
		super(cause);
	}
}
