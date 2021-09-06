package pl.springboot.crud.services;

import org.springframework.stereotype.Service;

import pl.springboot.crud.model.Order;

@Service
public interface OrderService {

	Order saveOrder(Order order);
}
