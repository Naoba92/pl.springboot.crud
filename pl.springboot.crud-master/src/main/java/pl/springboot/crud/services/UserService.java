package pl.springboot.crud.services;

import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.exception.UserAlreadyExistException;
import pl.springboot.crud.model.User;


public interface UserService {
	User registerNewUser(UserDTO newUser) throws UserAlreadyExistException;
}
