package com.wane.productservice.adapter.out.persistence;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntityRepository;
import com.wane.productservice.application.port.out.FindProductsPort;
import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FindProductsAdapter implements FindProductsPort {

	private final ProductEntityRepository productRepository;
	private final ProductMapper productMapper;

	@Override
	public CursorResponse<Product> findProductsWithCursor(Long productId, int size) {

		List<ProductEntity> productEntityList = productRepository.findProductsWithCursor(productId, size);

		List<Product> productList = productEntityList.stream()
				.map(productMapper::toDomainEntity)
				.toList();
		return new CursorResponse<>(productEntityList.size() == size, productList);

	}

}
