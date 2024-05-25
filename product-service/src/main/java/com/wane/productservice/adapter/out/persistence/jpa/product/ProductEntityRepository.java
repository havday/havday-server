package com.wane.productservice.adapter.out.persistence.jpa.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> , ProductEntityQuerydsl{

    List<ProductEntity> findAllByIdIn(List<Long> productIds);
}