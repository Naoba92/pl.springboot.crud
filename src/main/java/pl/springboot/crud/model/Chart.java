package pl.springboot.crud.model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Chart {
	private Map<Product, Integer> content;

	public Chart() {
		content = new HashMap<Product, Integer>();
	}

	public void addProduct(Product p) {
		content.put(p, content.getOrDefault(p, 0) + 1);
	}

	public void removeProduct(Product p) {
		int ocurrences = content.get(p);
		ocurrences--;
		if (ocurrences <= 0) {
			content.remove(p);
		} else {
			content.put(p, ocurrences);
		}
	}

	public double getSum() {
		return content.entrySet().stream().map(e -> e.getKey().getPrice() * e.getValue())
				.collect(Collectors.summingDouble(Double::valueOf));
	}
}
