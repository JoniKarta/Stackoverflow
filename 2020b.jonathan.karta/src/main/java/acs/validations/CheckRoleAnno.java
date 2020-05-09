/*
 * package acs.validations;
 * 
 * import java.lang.annotation.Documented; import
 * java.lang.annotation.ElementType; import java.lang.annotation.Retention;
 * import java.lang.annotation.RetentionPolicy; import
 * java.lang.annotation.Target;
 * 
 * import javax.validation.Constraint; import javax.validation.Payload;
 * 
 * @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
 * ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
 * 
 * @Retention(RetentionPolicy.RUNTIME)
 * 
 * @Documented
 * 
 * @Constraint(validatedBy = UserRoleValidator.class) public @interface
 * CheckRoleAnno { String regexp(); String message() default
 * "must match \"{regexp}\""; Class<?>[] groups() default {}; Class<? extends
 * Payload>[] payload() default {}; }
 */