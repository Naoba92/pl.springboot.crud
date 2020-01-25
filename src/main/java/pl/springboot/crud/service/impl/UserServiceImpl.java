package pl.springboot.crud.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.model.User;
import pl.springboot.crud.repository.UserRepository;
import pl.springboot.crud.repository.UserRoleRepository;
import pl.springboot.crud.services.UserService;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private EntityManager em;
	private PasswordEncoder passwordEncoder;
	@Override
	public User save(UserDTO userDTO) {
		return userRepository.saveAndFlush(User.builder()//
		.email(userDTO.getEmail())//
		.password(passwordEncoder.encode(userDTO.getPassword()))//
		.userName(userDTO.getUserName())//
		.role(userRoleRepository.findByAuthority("user_role"))//
		.build());//
		
	}
	@Override
	public List<User> findAll() {
		List<User> findAll = userRepository.findAll();
		return findAll;
	}
	@Override
	public User findByEmail(String email) {
		User user;
		try{
			 user = (User) em.createQuery("from User u where u.email = :email")
				.setParameter("email", email)
				.getSingleResult();}
		catch(NoResultException e) {
			user = null;
			
		}
		return user;
	}
	

}
