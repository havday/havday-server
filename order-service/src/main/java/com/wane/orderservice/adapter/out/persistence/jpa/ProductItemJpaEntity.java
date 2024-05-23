package com.wane.orderservice.adapter.out.persistence.jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "product_item")
public class ProductItemJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long productId;

	@Column(nullable = false)
	private int quantity;

	@Builder
	public ProductItemJpaEntity(Long id, Long productId, int quantity) {
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
	}
}
