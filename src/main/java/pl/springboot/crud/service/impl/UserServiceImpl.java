package pl.springboot.crud.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.springboot.crud.DTO.UserDTO;
import pl.springboot.crud.exception.UserAlreadyExistException;
import pl.springboot.crud.model.User;
import pl.springboot.crud.model.VerificationToken;
import pl.springboot.crud.repository.UserRepository;
import pl.springboot.crud.repository.UserRoleRepository;
import pl.springboot.crud.repository.VerificationTokenRepository;
import pl.springboot.crud.services.UserService;
@AllArgsConstructor
@Service
@Data
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private PasswordEncoder passwordEncoder;
	private VerificationTokenRepository tokenRepository;
	private MessageSource messages;

	@Override
	public User registerNewUser(UserDTO userDTO, WebRequest request) throws UserAlreadyExistException{
		if (emailExist(userDTO.getEmail())) {
            throw new UserAlreadyExistException(messages.getMessage("message.regError", null,"Użytkownik o podanym adresie email istnieje: ", request.getLocale())
               + userDTO.getEmail());
        }
		return userRepository.saveAndFlush(User.builder()//
		.email(userDTO.getEmail())//
		.password(passwordEncoder.encode(userDTO.getPassword()))//
		.userName(userDTO.getUserName())//
		.role(userRoleRepository.findByAuthority("user_role"))//
		.enabled(false)//
		.build());//
		
	}	
	private boolean emailExist(String email){
		return userRepository.findByEmail(email) != null;
	}
	@Override
	public User getUser(String verificationToken) {
		User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
	}
	@Override
	public void saveRegisteredUser(User user) {
		userRepository.save(user);
		
	}
	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
	}
	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}

	

}
