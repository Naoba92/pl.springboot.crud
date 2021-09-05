package pl.springboot.crud.service.impl;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.springboot.crud.model.User;
import pl.springboot.crud.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		if(userRepository.findByUsernameFetchRoles(userName) == null){
			throw new UsernameNotFoundException("Not Found");
		}
		if(userRepository.findByUsernameFetchRoles(userName).isEnabled()){
			return userRepository.findByUsernameFetchRoles(userName);	
		}
		return null;
	}
	

//
//	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        String[] userRoles = user.getRole().stream().map((role) -> role.getAuthority()).toArray(String[]::new);
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
//}
	}
