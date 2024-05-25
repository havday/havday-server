package com.wane.productservice.application.port.in;

import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;

import java.util.List;

public interface FindProductsUseCase {

    CursorResponse<Product> findProductsWithCursor(Long productId, int size);

    List<Product> findProductsByProductIdsIn(List<Long> productIds);
}
