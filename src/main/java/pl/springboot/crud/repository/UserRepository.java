package pl.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.springboot.crud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u from QuizUser u LEFT JOIN FETCH u.roles where u.email = :email")
	User findByEmailFetchRoles(@Param("email") String email);
}
