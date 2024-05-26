package com.wane.orderservice.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaEntityRepository extends JpaRepository<OrderJpaEntity, Long> {
    List<OrderJpaEntity> findByIdStartingWithOrderByIdDesc(String id);
}