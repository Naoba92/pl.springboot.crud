package pl.springboot.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.springboot.crud.model.Order;
import pl.springboot.crud.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

	//@Query("select o from Order o where o.userId = :userId")
	List<Order> findByUser(User user);
}
