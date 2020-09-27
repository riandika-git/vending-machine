package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Item {

	private Long id;

	private String name;
	
	private Integer price;

	private Integer quantity;
	
	public Item() {
	}

	public Item(Long id) {
		this.id = id;
	}


	public Item(Long id, String name, Integer price, Integer quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	@ApiModelProperty(value = "id", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(value = "price")
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	@ApiModelProperty(value = "quantityh")
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


}
