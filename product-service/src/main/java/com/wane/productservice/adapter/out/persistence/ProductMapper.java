package com.wane.productservice.adapter.out.persistence;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	public Product toDomainEntityForMain(ProductEntity productEntity) {
		return Product.forMain(
				productEntity.getId(),
				productEntity.getName(),
				productEntity.getPrice(),
				productEntity.getImageUrl()
		);
	}
}
