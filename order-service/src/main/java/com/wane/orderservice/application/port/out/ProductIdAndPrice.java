package com.wane.orderservice.application.port.out;

public record ProductIdAndPrice(
		Long id,
		int price
) {
}
