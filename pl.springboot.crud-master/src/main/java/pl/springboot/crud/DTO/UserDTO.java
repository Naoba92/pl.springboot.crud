package pl.springboot.crud.DTO;

import java.io.Serializable;

import javax.validation.constraints.Size;

import pl.springboot.crud.validation.annotation.SamePassword;
import pl.springboot.crud.validation.annotation.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@SamePassword
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1424453515682865483L;
	private String userName;
	@UniqueEmail
	private String email;
	private String confirmEmail;
	private String password;
	private String confirmPassword;
	
}
