package com.wane.productservice.adapter.out.persistence;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductDetailImageEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntityRepository;
import com.wane.productservice.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

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
        ProductDetailImageEntity image1 = ProductDetailImageEntity.builder().imageUrl("http://wan2daa.com/image1.png").build();
        ProductDetailImageEntity image2 = ProductDetailImageEntity.builder().imageUrl("http://wan2daa.com/image2.png").build();
        ProductDetailImageEntity image3 = ProductDetailImageEntity.builder().imageUrl("http://wan2daa.com/image3.png").build();


        ProductEntity product = ProductEntity.builder()
                .name("가방")
                .price(10000)
                .materialDescription("COTTON 100%")
                .sizeDescription("SIZE - FREE")
                .quantity(100)
                .mainImageUrl("testimage.png")
                .productDetailImages(List.of(image1, image2, image3))
                .build();

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