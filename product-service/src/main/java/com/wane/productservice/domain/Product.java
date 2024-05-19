package com.wane.productservice.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Product {

	private Long id;
	private String name;
	private int price;
	private String materialDescription;
	private String sizeDescription;
	private int quantity;
	private String mainImageUrl;
	private List<ProductDetailImage> productDetailImages;

	private Product(Long id, String name, int price, String materialDescription, String sizeDescription, int quantity, String mainImageUrl, List<ProductDetailImage> productDetailImages) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.materialDescription = materialDescription;
		this.sizeDescription = sizeDescription;
		this.quantity = quantity;
		this.mainImageUrl = mainImageUrl;
		this.productDetailImages = productDetailImages;
	}

	public static Product forMain(Long id, String name, int price, String mainImageUrl) {
		return new Product(id, name, price, "", "", 0, mainImageUrl, List.of());
	}

	public static Product forDetail(Long id, String name, int price, String materialDescription, String sizeDescription, String mainImageUrl, List<ProductDetailImage> productDetailImages) {
		return new Product(id, name, price, materialDescription, sizeDescription, 0, mainImageUrl, productDetailImages);
	}

}
