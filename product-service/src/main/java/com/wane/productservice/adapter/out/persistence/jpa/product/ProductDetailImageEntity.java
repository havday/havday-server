package com.wane.productservice.adapter.out.persistence.jpa.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "product_detail_image")
public class ProductDetailImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String imageUrl;

	public ProductDetailImageEntity(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}