package acs.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import acs.logic.RoleNotFoundException;

public class UserRoleValidator implements ConstraintValidator<CheckRoleAnno, Enum<?>> {
    private Pattern pattern;
 
    @Override
    public void initialize(CheckRoleAnno annotation) {
        try {
            pattern = Pattern.compile(annotation.regexp());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }
 
    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
    	Matcher m = pattern.matcher(value.name());
    	System.err.println("Value = " + value.name());
    	
        if (value != null && m.matches()) {
        	System.err.println("Call true");
            return true;
        } else {
        	System.err.println("Call Exception");
        	throw new RoleNotFoundException("Invalid role");
        }
    }
}