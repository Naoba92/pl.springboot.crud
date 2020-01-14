package pl.springboot.crud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class UserRole implements GrantedAuthority{
	private static final long serialVersionUID = 9103813255452348177L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long Id;
	private String authority;
	@Override
	public String getAuthority() {
		return authority;
	}

}
