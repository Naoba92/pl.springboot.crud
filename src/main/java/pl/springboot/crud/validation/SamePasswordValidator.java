package pl.springboot.crud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.validation.annotation.SamePassword;

public class SamePasswordValidator implements
		ConstraintValidator<SamePassword, Object> {

	@Override
	public void initialize(SamePassword arg0) {

	}

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserDTO user = (UserDTO) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
