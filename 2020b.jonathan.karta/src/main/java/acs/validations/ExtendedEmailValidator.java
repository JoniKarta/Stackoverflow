/*
 * package acs.validations;
 * 
 * import javax.validation.ConstraintValidator; import
 * javax.validation.ConstraintValidatorContext;
 * 
 * import acs.logic.InvalidEmailFormatException;
 * 
 * public class ExtendedEmailValidator implements
 * ConstraintValidator<ExtendedEmailAnno, String>{
 * 
 * @Override public void initialize(ExtendedEmailAnno constraintAnnotation) {
 * 
 * } //"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" //".+@.+\\..+"
 * 
 * @Override public boolean isValid(String emailField,
 * ConstraintValidatorContext cxt) { System.err.println("Does it work?");
 * if(emailField != null &&
 * emailField.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
 * return true; }else { return false; } } }
 */