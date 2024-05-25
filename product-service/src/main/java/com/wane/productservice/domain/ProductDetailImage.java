package com.wane.productservice.domain;

import lombok.Getter;

@Getter
public class ProductDetailImage {
	private Long id;
	private String imageUrl;

	public ProductDetailImage(Long id, String imageUrl) {
		this.id = id;
		this.imageUrl = imageUrl;
	}
}
