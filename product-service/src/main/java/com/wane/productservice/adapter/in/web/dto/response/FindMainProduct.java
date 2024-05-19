package com.wane.productservice.adapter.in.web.dto.response;

import com.wane.productservice.domain.Product;

public record FindMainProduct(
		Long id,
		String name,
		int price,
		String imageUrl
) {

	public static FindMainProduct fromDomainEntity(Product product) {
		return new FindMainProduct(product.getId(), product.getName(), product.getPrice(), product.getMainImageUrl());
	}
}
