package com.wane.orderservice.adapter.out.service.product;

public record ProductIdWithDecreaseQuantity(
        Long productId,
        int quantity
) {
}
