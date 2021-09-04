package pl.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.springboot.crud.model.User;
import pl.springboot.crud.model.VerificationToken;

public interface VerificationTokenRepository  extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

}
