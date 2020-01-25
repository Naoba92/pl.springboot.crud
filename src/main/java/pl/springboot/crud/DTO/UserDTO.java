package pl.springboot.crud.DTO;

import javax.validation.constraints.Size;

import pl.springboot.crud.validation.annotation.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {

	private String userName;
	@UniqueEmail
	private String email;
	private String confirmEmail;
	@Size(min=4)
	private String password;
	private String confirmPassword;
	
}
