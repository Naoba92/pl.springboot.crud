package pl.springboot.crud.controller;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

import pl.springboot.crud.model.Chart;
import pl.springboot.crud.model.Order;
import pl.springboot.crud.repository.OrderRepository;
import pl.springboot.crud.services.OrderService;

@Controller
@AllArgsConstructor
public class OrderController {

	private OrderService orderServ;
	private OrderRepository orderRepository;

	@PostMapping("/")
	public String mainView(ModelMap model, List<Order> orders){
		model.addAttribute("orders", orders);
		return "index";
	}
	@PostMapping("/buy")
	public String addOrder(ModelMap model , Chart chart){
		Order order = new Order(chart);
		orderServ.saveOrder(order);
	    return "index";
	}
}
