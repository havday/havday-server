package com.wane.orderservice.application.port.out;

import com.wane.orderservice.domain.ProductItem;

import java.util.List;

public interface DecreaseProductQuantityPort {

    void decreaseProductQuantity(List<ProductItem> productIdAndQuantityList);
}
