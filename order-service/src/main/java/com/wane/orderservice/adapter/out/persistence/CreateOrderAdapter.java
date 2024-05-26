package com.wane.orderservice.adapter.out.persistence;

import com.wane.orderservice.adapter.out.persistence.jpa.OrderJpaEntity;
import com.wane.orderservice.adapter.out.persistence.jpa.OrderJpaEntityRepository;
import com.wane.orderservice.application.port.out.CreateOrderPort;
import com.wane.orderservice.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CreateOrderAdapter implements CreateOrderPort {
    private final OrderJpaEntityRepository orderJpaEntityRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order createOrder(Order order) {
        OrderJpaEntity orderJpaEntity = orderMapper.toJpaEntity(order);

        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        List<OrderJpaEntity> orderJpaEntityList = orderJpaEntityRepository.findByIdStartingWithOrderByIdDesc(currentDate);

        if (orderJpaEntityList.isEmpty()) {
            String newId = currentDate + String.format("%07d", 1);
            orderJpaEntity.setId(newId);
        } else {
            String lastId = orderJpaEntityList.getFirst().getId();
            String newId = currentDate + String.format("%07d", Integer.parseInt(lastId.substring(8)) + 1);
            orderJpaEntity.setId(newId);
        }
        OrderJpaEntity savedOrderJpaEntity = orderJpaEntityRepository.save(orderJpaEntity);

        return orderMapper.toDomainEntity(savedOrderJpaEntity);
    }
}
