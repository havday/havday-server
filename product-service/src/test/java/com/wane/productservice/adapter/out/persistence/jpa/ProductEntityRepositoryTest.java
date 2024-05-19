package com.wane.productservice.adapter.out.persistence.jpa;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntityRepository;
import com.wane.productservice.config.QuerydslConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
class ProductEntityRepositoryTest {

	@Autowired
	private ProductEntityRepository productRepository;


	@DisplayName("offset을 사용하지 않고, 제품들을 반환한다.")
	@Test
	void findProductsByIdGreaterThan() {
		//given
		ProductEntity product1 = createProductJpaEntity("모자", 10000);
		ProductEntity product2 = createProductJpaEntity("신발", 15000);
		ProductEntity product3 = createProductJpaEntity("옷", 100000);

		productRepository.saveAll(List.of(product1, product2, product3));

		//when
		List<ProductEntity> products = productRepository.findProductsWithCursor(0L, 2);

		//then
		assertThat(products).hasSize(2);
		assertThat(products.get(0).getName()).isEqualTo("모자");
		assertThat(products.get(1).getName()).isEqualTo("신발");
	}

	private ProductEntity createProductJpaEntity(String name, int price) {
		return new ProductEntity(
				name,
				price,
				"COTTON 100%",
				"""
						SIZE - FREE

						VISOR - 7

						DEPTH - 15.5""",
				100,
				"",
				null
		);
	}


}