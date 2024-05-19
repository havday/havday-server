package com.wane.productservice.domain;

import lombok.Getter;

@Getter
public class Product {

	private Long id;
	private String name;
	private int price;
	private String materialDescription;
	private String sizeDescription;
	private int quantity;

	private Product(Long id, String name, int price, String materialDescription, String sizeDescription, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.materialDescription = materialDescription;
		this.sizeDescription = sizeDescription;
		this.quantity = quantity;
	}

	public static Product of(Long id, String name, int price, String materialDescription, String sizeDescription, int quantity) {
		return new Product(id, name, price, materialDescription, sizeDescription, quantity);
	}

}
