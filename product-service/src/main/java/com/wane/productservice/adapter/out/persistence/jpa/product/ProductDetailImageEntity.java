package com.wane.productservice.adapter.out.persistence.jpa.product;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "product_detail_image")
public class ProductDetailImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String imageUrl;

	@Builder
	public ProductDetailImageEntity(Long id, String imageUrl) {
		this.id = id;
		this.imageUrl = imageUrl;
	}


}