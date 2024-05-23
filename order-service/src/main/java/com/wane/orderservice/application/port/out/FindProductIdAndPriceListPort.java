package com.wane.orderservice.application.port.out;

import java.util.List;

public interface FindProductIdAndPriceListPort {


	List<ProductIdAndPrice> findProductIdAndPriceListByProductIds(List<Long> productIds);
}
