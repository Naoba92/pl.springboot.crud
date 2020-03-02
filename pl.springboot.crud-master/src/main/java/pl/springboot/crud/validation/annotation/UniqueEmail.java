package pl.springboot.crud.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.springboot.crud.validation.UniqueEmailValidator;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
	String message() default "Podany email istnieje juz w bazie danych.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
