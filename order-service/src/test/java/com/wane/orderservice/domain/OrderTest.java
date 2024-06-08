package com.wane.orderservice.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderTest {

    @Test
    public void createOrderSuccessfullyWithNoBankBookPayment() {
        Order order = Order.createOrder(1L, 1L, 10000, true, 10000, PaymentType.NO_BANKBOOK, Collections.emptyList());

        assertThat(order).isNotNull();
        assertThat(order.getMemberId()).isEqualTo(1L);
        assertThat(order.getAddressId()).isEqualTo(1L);
        assertThat(order.getTotalPrice()).isEqualTo(10000);
        assertThat(order.isDeliveryFeeExists()).isTrue();
        assertThat(order.getUsedPoint()).isEqualTo(10000);
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.BEFORE_DEPOSIT);
        assertThat(order.getPayment()).isInstanceOf(Payment.class);
    }

    @Test
    public void createOrderSuccessfullyWithCardPayment() {
        Order order = Order.createOrder(1L, 1L, 10000, true, 10000, PaymentType.CARD, Collections.emptyList());

        assertThat(order).isNotNull();
        assertThat(order.getMemberId()).isEqualTo(1L);
        assertThat(order.getAddressId()).isEqualTo(1L);
        assertThat(order.getTotalPrice()).isEqualTo(10000);
        assertThat(order.isDeliveryFeeExists()).isTrue();
        assertThat(order.getUsedPoint()).isEqualTo(10000);
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.PREPARED);
        assertThat(order.getPayment()).isInstanceOf(Payment.class);
    }

    @Test
    public void createOrderThrowsExceptionWithUnsupportedPaymentType() {
        assertThatThrownBy(() -> Order.createOrder(1L, 1L, 10000, true, 10000, null, Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않은 결제 타입입니다.");
    }
}