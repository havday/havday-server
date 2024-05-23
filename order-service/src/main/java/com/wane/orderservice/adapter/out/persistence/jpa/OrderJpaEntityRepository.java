package com.wane.orderservice.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaEntityRepository extends JpaRepository<OrderJpaEntity, Long> {
}