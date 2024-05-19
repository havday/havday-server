package com.wane.productservice.application.port.in;

import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;

public interface FindProductsUseCase {

	CursorResponse<Product> findProductsWithCursor(Long productId, int size);
}
