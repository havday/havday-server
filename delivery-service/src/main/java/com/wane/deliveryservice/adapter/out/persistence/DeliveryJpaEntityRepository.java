package com.wane.deliveryservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryJpaEntityRepository extends JpaRepository<DeliveryJpaEntity, Long> {
}