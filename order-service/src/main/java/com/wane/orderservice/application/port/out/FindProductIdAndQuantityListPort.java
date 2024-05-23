package com.wane.orderservice.application.port.out;

import java.util.List;

public interface FindProductIdAndQuantityListPort {

    List<ProductIdAndQuantity> findProductIdAndQuantityListByProductIds(List<Long> productIds);
}
