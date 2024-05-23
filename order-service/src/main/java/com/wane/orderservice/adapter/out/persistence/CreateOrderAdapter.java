package com.wane.orderservice.adapter.out.persistence;

import com.wane.orderservice.adapter.out.persistence.jpa.OrderJpaEntity;
import com.wane.orderservice.adapter.out.persistence.jpa.OrderJpaEntityRepository;
import com.wane.orderservice.application.port.out.CreateOrderPort;
import com.wane.orderservice.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CreateOrderAdapter implements CreateOrderPort {
	private final OrderJpaEntityRepository orderJpaEntityRepository;
	private final OrderMapper orderMapper;

	@Override
	public Order createOrder(Order order) {
		OrderJpaEntity orderJpaEntity = orderMapper.toJpaEntity(order);
		OrderJpaEntity savedOrderJpaEntity = orderJpaEntityRepository.save(orderJpaEntity);

		return orderMapper.toDomainEntity(savedOrderJpaEntity);
	}
}
