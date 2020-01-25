package pl.springboot.crud.validation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import pl.springboot.crud.services.UserService;
import pl.springboot.crud.validation.annotation.UniqueEmail;


@Service
@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private UserService userService;

	@Override
	public void initialize(UniqueEmail arg0) {

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext ctx) {
		System.out.println(userService.findByEmail(email));
		if(userService.findByEmail(email) != null){
			return false;
		}
		return true;
	}

}
