package com.wane.productservice.adapter.out.persistence;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductDetailImageEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductDetailImageEntityRepository;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntityRepository;
import com.wane.productservice.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class FindProductAdapterTest {

	@Autowired
	private FindProductAdapter findProductAdapter;

	@MockBean
	private ProductEntityRepository productEntityRepository;

	@DisplayName("product entity를 id 값으로 반환한다.")
	@Test
	void findProductById() {
		//given
		ProductDetailImageEntity image1 = new ProductDetailImageEntity("http://wan2daa.com/image1.png");
		ProductDetailImageEntity image2 = new ProductDetailImageEntity("http://wan2daa.com/image2.png");
		ProductDetailImageEntity image3 = new ProductDetailImageEntity("http://wan2daa.com/image3.png");

		ProductEntity product = new ProductEntity(
				"가방",
				10000,
				"COTTON 100%",
				"SIZE - FREE",
				100,
				"testimage.png",
				null,
				List.of(image1, image2, image3)
		);
		given(productEntityRepository.findById(anyLong()))
				.willReturn(java.util.Optional.of(product));

		//when
		Product sut = findProductAdapter.findProductById(1L);

		//then
		assertThat(sut.getName()).isEqualTo("가방");
		assertThat(sut.getPrice()).isEqualTo(10000);
		assertThat(sut.getProductDetailImages()).hasSize(3);
	}


}