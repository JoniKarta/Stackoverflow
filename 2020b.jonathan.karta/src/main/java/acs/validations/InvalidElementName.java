package acs.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidElementName extends RuntimeException {


	private static final long serialVersionUID = 8068799640785677932L;

	public InvalidElementName() {
		super();
	}

	public InvalidElementName(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidElementName(String message) {
		super(message);
	}

	public InvalidElementName(Throwable cause) {
		super(cause);
	}
}
