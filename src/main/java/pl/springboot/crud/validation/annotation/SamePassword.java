package pl.springboot.crud.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.springboot.crud.validation.SamePasswordValidator;


@Target(value = {ElementType.TYPE,ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SamePasswordValidator.class)
@Documented
public @interface SamePassword {
	String message() default "Wprowadzone hasła nie są identyczne";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}