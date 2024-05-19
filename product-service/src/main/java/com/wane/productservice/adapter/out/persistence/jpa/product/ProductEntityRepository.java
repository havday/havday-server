package com.wane.productservice.adapter.out.persistence.jpa.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> , ProductEntityQuerydsl{

}