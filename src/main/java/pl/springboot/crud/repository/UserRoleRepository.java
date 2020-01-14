package pl.springboot.crud.repository;

import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.springboot.crud.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	@Cacheable("userRoleByAuthority")
	Set<UserRole> findByAuthority(String authority);
}
