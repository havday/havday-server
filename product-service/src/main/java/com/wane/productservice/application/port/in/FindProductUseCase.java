package com.wane.productservice.application.port.in;

import com.wane.productservice.domain.Product;

public interface FindProductUseCase {

	Product findProduct(Long productId);
}
