package com.wane.productservice;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class InitService {

    private final ProductEntityRepository productEntityRepository;

    @PostConstruct
    public void init() {
        ProductEntity product1 = createProductJpaEntity("모자", 10000);
        ProductEntity product2 = createProductJpaEntity("신발", 15000);
        ProductEntity product3 = createProductJpaEntity("옷", 100000);

        productEntityRepository.saveAll(List.of(product1, product2, product3));

        log.info("product 1 Id = {}", product1.getId());
        log.info("product 2 Id = {}", product2.getId());
        log.info("product 3 Id = {}", product3.getId());
    }

    private ProductEntity createProductJpaEntity(String name, int price) {
        return ProductEntity.builder()
                .name(name)
                .price(price)
                .materialDescription("COTTON 100%")
                .sizeDescription("""
						SIZE - FREE

						VISOR - 7

						DEPTH - 15.5""")
                .quantity(100)
                .mainImageUrl("")
                .build();
    }
}
