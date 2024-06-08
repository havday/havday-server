package com.wane.orderservice.adapter.out.persistence;

import com.wane.orderservice.adapter.out.persistence.jpa.OrderJpaEntity;
import com.wane.orderservice.adapter.out.persistence.jpa.PaymentJpaEntity;
import com.wane.orderservice.adapter.out.persistence.jpa.ProductItemJpaEntity;
import com.wane.orderservice.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {

    @InjectMocks
    private OrderMapper orderMapper;

    @DisplayName("Maps Order domain object to OrderJpaEntity")
    @Test
    void toJpaEntityMapsOrderToOrderJpaEntity() {
        Payment payment = Payment.forMapper(1L, 1000, PaymentType.CARD, PaymentStatus.BEFORE_PAYED, null);
        Order order = Order.of("2024060800001", 1L, 1L, 1000, true, 100, OrderStatus.PREPARED, null, payment, Collections.emptyList());

        OrderJpaEntity orderJpaEntity = orderMapper.toJpaEntity(order);

        assertThat(orderJpaEntity.getMemberId()).isEqualTo(order.getMemberId());
        assertThat(orderJpaEntity.getAddressId()).isEqualTo(order.getAddressId());
        assertThat(orderJpaEntity.getTotalPrice()).isEqualTo(order.getTotalPrice());
        assertThat(orderJpaEntity.isDeliveryFeeExists()).isEqualTo(order.isDeliveryFeeExists());
        assertThat(orderJpaEntity.getUsedPoint()).isEqualTo(order.getUsedPoint());
        assertThat(orderJpaEntity.getOrderStatus()).isEqualTo(order.getOrderStatus());
    }

    @DisplayName("Maps Payment domain object to PaymentJpaEntity")
    @Test
    void toJpaEntityMapsPaymentToPaymentJpaEntity() {
        Payment payment = Payment.forMapper(1L, 1000, PaymentType.CARD, PaymentStatus.BEFORE_PAYED, null);

        PaymentJpaEntity paymentJpaEntity = orderMapper.toJpaEntity(payment);

        assertThat(paymentJpaEntity.getPrice()).isEqualTo(payment.getPrice());
        assertThat(paymentJpaEntity.getPaymentType()).isEqualTo(payment.getPaymentType());
        assertThat(paymentJpaEntity.getPaymentStatus()).isEqualTo(payment.getPaymentStatus());
    }

    @DisplayName("Maps ProductItem domain object to ProductItemJpaEntity")
    @Test
    void toJpaEntityMapsProductItemToProductItemJpaEntity() {
        ProductItem productItem = ProductItem.create(1L, 1);

        ProductItemJpaEntity productItemJpaEntity = orderMapper.toJpaEntity(productItem);

        assertThat(productItemJpaEntity.getProductId()).isEqualTo(productItem.getProductId());
        assertThat(productItemJpaEntity.getQuantity()).isEqualTo(productItem.getQuantity());
    }
}