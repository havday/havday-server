package com.wane.productservice.domain;

import lombok.Getter;

public class ProductDetailImage {

	private Long id;

	@Getter
	private String imageUrl;

	public ProductDetailImage(Long id, String imageUrl) {
		this.id = id;
		this.imageUrl = imageUrl;
	}
}
