package com.wane.productservice.adapter.out.persistence.mapper;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.domain.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Component
public class ProductMapper {

	public static Product toDomainEntityForMain(ProductEntity productEntity) {
		return Product.forMain(
				productEntity.getId(),
				productEntity.getName(),
				productEntity.getPrice(),
				productEntity.getMainImageUrl()
		);
	}

	public static Product toDomainEntityForDetail(ProductEntity productEntity) {
		return Product.forDetail(
				productEntity.getId(),
				productEntity.getName(),
				productEntity.getPrice(),
				productEntity.getMaterialDescription(),
				productEntity.getSizeDescription(),
				productEntity.getMainImageUrl(),
				productEntity.getProductDetailImages().stream().map(ProductDetailImageMapper::toDomainEntity).toList()
		);
	}
}
