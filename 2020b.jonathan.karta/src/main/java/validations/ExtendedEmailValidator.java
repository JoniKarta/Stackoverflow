package validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import acs.logic.ExtendedEmailAnno;

public class ExtendedEmailValidator implements ConstraintValidator<ExtendedEmailAnno, String>{

	@Override
	public void initialize(ExtendedEmailAnno constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
		// TODO Auto-generated method stub
		if(emailField != null && emailField.matches(".+@.+\\..+")){
			return true;
		}else {
			throw new RuntimeException("Wrong mail");
		}
		//return emailField != null && emailField.matches(".+@.+\\..+");
	}

}
