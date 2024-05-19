package com.wane.productservice.application.port.out;

import com.wane.productservice.domain.Product;

public interface FindProductPort {
	Product findProductById(Long productId);
}
