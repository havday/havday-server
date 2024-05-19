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
	private String imageUrl;

	private Product(Long id, String name, int price, String materialDescription, String sizeDescription, int quantity, String imageUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.materialDescription = materialDescription;
		this.sizeDescription = sizeDescription;
		this.quantity = quantity;
		this.imageUrl = imageUrl;
	}

	public static Product of(Long id, String name, int price, String materialDescription, String sizeDescription, int quantity, String imageUrl) {
		return new Product(id, name, price, materialDescription, sizeDescription, quantity, imageUrl);
	}

}
