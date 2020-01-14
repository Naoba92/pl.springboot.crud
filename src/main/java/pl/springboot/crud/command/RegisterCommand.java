package pl.springboot.crud.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.springboot.crud.model.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterCommand {

	private User user;
	private String userName;
	private String email;
	private String confirmEmail;
	private String password;
	private String confirmPassword;
	
}
