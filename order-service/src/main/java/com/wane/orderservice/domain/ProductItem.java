package com.wane.orderservice.domain;

import lombok.Getter;

@Getter
public class ProductItem {

	private Long id;
	private Long productId;
	private int quantity;

	private ProductItem(Long id, Long productId, int quantity) {
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
	}

	private ProductItem(Long productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public static ProductItem forMapper(Long id, Long productId, int quantity) {
		return new ProductItem(id, productId, quantity);
	}

	public static ProductItem create(Long productId, int quantity) {
		return new ProductItem(productId, quantity);
	}
}
