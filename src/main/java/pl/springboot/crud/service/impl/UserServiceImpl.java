package pl.springboot.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pl.springboot.crud.model.User;
import pl.springboot.crud.repository.UserRepository;
import pl.springboot.crud.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public User save(User newUser) {
		return userRepository.save(newUser);
	}

}
