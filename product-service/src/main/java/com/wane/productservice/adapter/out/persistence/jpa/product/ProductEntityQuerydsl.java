package com.wane.productservice.adapter.out.persistence.jpa.product;

import java.util.List;

public interface ProductEntityQuerydsl {

	List<ProductEntity> findProductsOrderByIdAscWithCursor(Long productId, int limit);
}
