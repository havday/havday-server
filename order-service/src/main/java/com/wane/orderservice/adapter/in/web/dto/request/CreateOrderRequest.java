package com.wane.orderservice.adapter.in.web.dto.request;

import java.util.List;

public record CreateOrderRequest(
		Long addressId,
		int totalPrice,
		boolean isDeliveryFeeExists,
		int usedPoint,
		String paymentType,
		List<ProductItemRequest> productItems
) {
}
