package pl.springboot.crud.services;

import org.springframework.web.context.request.WebRequest;

import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.exception.UserAlreadyExistException;
import pl.springboot.crud.model.User;
import pl.springboot.crud.model.VerificationToken;


public interface UserService {
	User registerNewUser(UserDTO newUser, WebRequest request) throws UserAlreadyExistException;
	
	User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}
