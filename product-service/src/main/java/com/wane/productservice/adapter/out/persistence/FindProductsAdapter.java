package com.wane.productservice.adapter.out.persistence;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntityRepository;
import com.wane.productservice.application.port.out.FindProductsPort;
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
	public List<Product> findProductsOrderByIdAscWithCursor(Long productId, int size) {

		List<ProductEntity> productEntityList = productRepository.findProductsOrderByIdAscWithCursor(productId, size);

		return productEntityList.stream()
				.map(productMapper::toDomainEntityForMain)
				.toList();

	}

}
