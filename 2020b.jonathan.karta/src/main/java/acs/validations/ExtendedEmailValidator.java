package acs.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
			throw new RuntimeException();
		}
		//return emailField != null && emailField.matches(".+@.+\\..+");
	}

}
