package pl.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.springboot.crud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u from User u LEFT JOIN FETCH u.role where u.userName = :userName")
	User findByUsernameFetchRoles(@Param("userName") String userName);
	
//	@Query("SELECT u from User u LEFT JOIN FETCH u.role where u.email = :email")
//	String findByEmailFetchRoles(@Param("email") String email);




}
