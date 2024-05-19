package com.wane.productservice.adapter.in.web.dto.response;

import com.wane.productservice.domain.Product;

public record FindProduct(
		Long id,
		String name,
		int price,
		String imageUrl
) {

	public static FindProduct fromDomainEntity(Product product) {
		return new FindProduct(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
	}
}
