package pl.springboot.crud.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.springboot.crud.model.Chart;
import pl.springboot.crud.model.Product;

@Controller
public class ProductController {
	private List<Product> products;

//	@PostConstruct
//	public void init() {
//		products = Arrays.asList(//
//				new Product(1, "Cola", "napoje", 5.5),//
//				new Product(2, "Woda mineralna", "napoje", 2.5),//
//				new Product(3, "Fanta", "napoje", 5.0),//
//				new Product(4, "Sprite", "napoje", 5.25)//
//				);//
//	}

	@GetMapping("/chart")
	public String mainView(ModelMap model, Chart chart) {
		model.addAttribute("availableProducts", products);
		model.addAttribute("chart", chart);
		return "index";
	}

	@PostMapping("/addToChart")
	public String addToChart(ModelMap model, @RequestParam("id") int productId,
			Chart chart) {
		Product toAdd = products.stream().filter(f -> f.getId() == productId)
				.findAny().get();
		chart.addProduct(toAdd);
		return mainView(model, chart);
	}

	@PostMapping("/deleteFromChart")
	public String deleteFromChart(ModelMap model,
			@RequestParam("id") int productId, Chart chart) {
		Product toRemove = products.stream()
				.filter(f -> f.getId() == productId).findAny().get();
		chart.removeProduct(toRemove);
		return mainView(model, chart);

	}
}
