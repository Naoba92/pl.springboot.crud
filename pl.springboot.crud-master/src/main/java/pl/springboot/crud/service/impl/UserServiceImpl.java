package pl.springboot.crud.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.exception.UserAlreadyExistException;
import pl.springboot.crud.model.User;
import pl.springboot.crud.repository.UserRepository;
import pl.springboot.crud.repository.UserRoleRepository;
import pl.springboot.crud.services.UserService;
@AllArgsConstructor
@Service
@Data
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerNewUser(UserDTO userDTO) throws UserAlreadyExistException{
		if (emailExist(userDTO.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
              + userDTO.getEmail());
        }
		return userRepository.saveAndFlush(User.builder()//
		.email(userDTO.getEmail())//
		.password(passwordEncoder.encode(userDTO.getPassword()))//
		.userName(userDTO.getUserName())//
		.role(userRoleRepository.findByAuthority("user_role"))//
		.build());//
		
	}	
	private boolean emailExist(String email){
		return userRepository.findByEmail(email) != null;
	}

	

}
