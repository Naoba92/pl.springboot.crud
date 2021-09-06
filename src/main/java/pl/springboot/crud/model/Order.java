package pl.springboot.crud.model;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import lombok.Data;

@Data
public class Order {

	private static final long serialVersionUID = -1170586370972618350L;
	private int id;
	private List<Product> productsInOrderList;
	private Date date;
	public Order(Chart chart) {
		for(Entry<Product, Integer> para : chart.getContent().entrySet()){
			for(int i = 0 ; i<para.getValue(); i++){
				productsInOrderList.add(para.getKey());
			}
		}
		this.date = new Date();
	}
}
