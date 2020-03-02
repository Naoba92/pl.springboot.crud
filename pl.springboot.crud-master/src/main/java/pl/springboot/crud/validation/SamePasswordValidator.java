package pl.springboot.crud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.validation.annotation.SamePassword;

public class SamePasswordValidator implements
		ConstraintValidator<SamePassword, UserDTO> {

	@Override
	public void initialize(SamePassword arg0) {

	}

	@Override
	public boolean isValid(UserDTO form, ConstraintValidatorContext ctx) {
		if (StringUtils.isEmpty(form.getPassword())) {
			ctx.buildConstraintViolationWithTemplate("Hasło nie może być puste")
					.addPropertyNode("password").addConstraintViolation();
			return false;
		}
		ctx.buildConstraintViolationWithTemplate("Hasła nie są takie same")
				.addPropertyNode("password").addConstraintViolation();
		return form.getPassword().equals(form.getConfirmPassword());
	}
}
