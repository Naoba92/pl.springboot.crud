package pl.springboot.crud.DTO;

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
	private String email;
	private String confirmEmail;
	private String password;
	private String confirmPassword;
	
}
