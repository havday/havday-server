package com.wane.productservice.adapter.out.persistence;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntityRepository;
import com.wane.productservice.adapter.out.persistence.mapper.ProductMapper;
import com.wane.productservice.application.port.out.FindProductPort;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FindProductAdapter implements FindProductPort {

	private final ProductEntityRepository productEntityRepository;

	@Override
	public Product findProductById(Long productId) {
		ProductEntity productEntity = productEntityRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		return ProductMapper.toDomainEntityForDetail(productEntity);
	}
}
