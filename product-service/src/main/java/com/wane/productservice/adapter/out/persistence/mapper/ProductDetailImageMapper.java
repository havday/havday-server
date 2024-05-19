package com.wane.productservice.adapter.out.persistence.mapper;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductDetailImageEntity;
import com.wane.productservice.domain.ProductDetailImage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Component
public class ProductDetailImageMapper {


	public static ProductDetailImage toDomainEntity(ProductDetailImageEntity productDetailImageEntity) {
		return new ProductDetailImage(
				productDetailImageEntity.getId(),
				productDetailImageEntity.getImageUrl()
		);
	}
}
