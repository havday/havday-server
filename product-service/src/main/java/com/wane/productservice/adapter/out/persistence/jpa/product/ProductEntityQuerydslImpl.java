package com.wane.productservice.adapter.out.persistence.jpa.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.wane.productservice.adapter.out.persistence.jpa.product.QProductEntity.productEntity;

@RequiredArgsConstructor
public class ProductEntityQuerydslImpl implements ProductEntityQuerydsl {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<ProductEntity> findProductsOrderByIdAscWithCursor(Long productId, int limit) {
		return queryFactory.selectFrom(productEntity)
				.where(productEntity.id.gt(productId))
				.orderBy(productEntity.id.asc())
				.limit(limit)
				.fetch();
	}
}
