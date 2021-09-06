package pl.springboot.crud.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.springboot.crud.model.Order;
import pl.springboot.crud.repository.OrderRepository;
import pl.springboot.crud.services.OrderService;

@AllArgsConstructor
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderReposiotory;
	
	@Override
	public Order saveOrder(Order order) {
		return orderReposiotory.save(order);
	}

}
