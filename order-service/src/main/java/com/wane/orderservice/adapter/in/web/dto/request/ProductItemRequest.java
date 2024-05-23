package com.wane.orderservice.adapter.in.web.dto.request;

public record ProductItemRequest(
		Long productId,
		int quantity
) {
}
