package pl.springboot.crud.model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Chart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ElementCollection
	@CollectionTable(name = "products_to_orders" )       
	@MapKeyJoinColumn(name = "product_id")
	@Column(name = "product_quantity")
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
