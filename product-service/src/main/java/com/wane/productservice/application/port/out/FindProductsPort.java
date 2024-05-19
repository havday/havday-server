package com.wane.productservice.application.port.out;

import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;

public interface FindProductsPort {

	CursorResponse<Product> findProductsWithCursor(Long productId, int size);
}
