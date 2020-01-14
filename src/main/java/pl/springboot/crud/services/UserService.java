package pl.springboot.crud.services;

import java.util.List;

import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.model.User;


public interface UserService {
	User save(UserDTO newUser);
	List<User> findAll();
}
