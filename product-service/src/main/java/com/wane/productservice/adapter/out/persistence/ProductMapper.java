package com.wane.productservice.adapter.out.persistence;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	public Product toDomainEntity(ProductEntity productEntity) {
		return Product.of(
				productEntity.getId(),
				productEntity.getName(),
				productEntity.getPrice(),
				productEntity.getMaterialDescription(),
				productEntity.getSizeDescription(),
				productEntity.getQuantity()
		);
	}
}
