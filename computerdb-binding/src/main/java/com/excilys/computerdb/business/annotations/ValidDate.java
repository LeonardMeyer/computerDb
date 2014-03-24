package com.excilys.computerdb.business.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.excilys.computerdb.business.annotations.validation.DateValidator;

@Documented
@Retention(RUNTIME)
@Target(value = { FIELD, ANNOTATION_TYPE })
@Constraint(validatedBy = { DateValidator.class })
public @interface ValidDate {

	String message() default "";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
