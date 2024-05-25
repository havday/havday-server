package com.wane.productservice.adapter.out.persistence;

import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntityRepository;
import com.wane.productservice.adapter.out.persistence.mapper.ProductMapper;
import com.wane.productservice.application.port.out.SaveProductsPort;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SaveProductsAdapter implements SaveProductsPort {

    private final ProductMapper productMapper;
    private final ProductEntityRepository productEntityRepository;

    @Override
    public void saveProducts(List<Product> products) {
        List<ProductEntity> productJpaEntities = products.stream().map(productMapper::toJpaEntity).toList();

        productEntityRepository.saveAll(productJpaEntities);
    }
}
