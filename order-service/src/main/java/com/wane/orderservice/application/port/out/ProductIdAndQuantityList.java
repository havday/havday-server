package com.wane.orderservice.application.port.out;

import java.util.List;

public record ProductIdAndQuantityList(
        List<ProductIdAndQuantity> products
) {
}
