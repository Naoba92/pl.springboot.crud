package pl.springboot.crud.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import pl.springboot.crud.validation.annotation.SamePassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SamePassword 
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1424453515682865483L;
	@NotNull
	@NotEmpty
	private String userName;
	@NotNull
	@NotEmpty
	@Email (message= "Niepoprawny format email")
	private String email;
	@NotNull
	@NotEmpty
	@Length(min = 6, message = "Hasło musi mieć 6 lub więcej znaków")
	private String password;
	private String confirmPassword;
	
}
