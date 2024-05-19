package com.wane.productservice.adapter.out.persistence.jpa.productcategory;


import com.wane.productservice.adapter.out.persistence.jpa.category.CategoryEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProductCategoryEntity implements Persistable<ProductCategoryKey> {

	@EmbeddedId
	private ProductCategoryKey id;

	@MapsId("productId")
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@MapsId("categoryId")
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	@CreatedDate
	private LocalDateTime createdDateTime;

	@Override
	public boolean isNew() {
		return createdDateTime == null;
	}

	public void setParent(ProductEntity product) {
		this.product = product;
		this.id = new ProductCategoryKey(product.getId(), category.getId());
	}
}

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
class ProductCategoryKey implements Serializable {

	private Long productId;

	private Long categoryId;
}

