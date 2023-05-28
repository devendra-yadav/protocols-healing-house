package com.healinghouse.protocolshealinghouse.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = ImageFileValidator.class)
public @interface ValidImageFile {
	
	String message() default "Please provide a valid image file";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
