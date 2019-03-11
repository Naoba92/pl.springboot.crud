package pl.springboot.crud.services;

import pl.springboot.crud.model.User;


public interface UserService {
	User save(User newUser);
}
