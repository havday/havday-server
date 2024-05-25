package com.wane.productservice.adapter.in.web.dto.request;

public record ProductIdAndDecreaseQuantity(
        Long productId,
        int quantity
) {
}
