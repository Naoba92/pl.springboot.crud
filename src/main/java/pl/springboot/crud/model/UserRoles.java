package pl.springboot.crud.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

public class UserRoles implements GrantedAuthority {
	
	private static final long serialVersionUID = -7285889338335465548L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String authority;

	@Override
	public String getAuthority() {
		return authority;
}
}
