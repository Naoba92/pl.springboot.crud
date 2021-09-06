package pl.springboot.crud.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

	private static final long serialVersionUID = 151553012019225417L;
	private int id;
	private String name;
	private String category;
	private double price;
}
