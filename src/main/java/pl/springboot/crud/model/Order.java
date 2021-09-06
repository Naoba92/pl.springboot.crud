package pl.springboot.crud.model;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name ="\"order\"")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CHART_IN_ORDER", joinColumns = { @JoinColumn(name = "ORDER_ID") }, inverseJoinColumns = { @JoinColumn(name = "CHART_ID") })
	private List<Product> productsInOrderList;
	private Date dateOfOrder;
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
	public Order(Chart chart) {
		for(Entry<Product, Integer> pair : chart.getContent().entrySet()){
			for(int i = 0 ; i<pair.getValue(); i++){
				productsInOrderList.add(pair.getKey());
			}
		}
		this.dateOfOrder = new Date();
	}
}
