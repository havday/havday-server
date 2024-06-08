package com.wane.orderservice.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    @Test
    public void createNoBankBookPaymentSuccessfully() {
        Payment payment = Payment.createNoBankBookPayment(10000);

        assertThat(payment).isNotNull();
        assertThat(payment.getPrice()).isEqualTo(10000);
        assertThat(payment.getPaymentType()).isEqualTo(PaymentType.NO_BANKBOOK);
        assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.BEFORE_PAYED);
    }

    @Test
    public void createCardPaymentSuccessfully() {
        Payment payment = Payment.createCardPayment(10000);

        assertThat(payment).isNotNull();
        assertThat(payment.getPrice()).isEqualTo(10000);
        assertThat(payment.getPaymentType()).isEqualTo(PaymentType.CARD);
        assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
    }

    @Test
    public void forMapperCreatesPaymentSuccessfully() {
        LocalDateTime now = LocalDateTime.now();
        Payment payment = Payment.forMapper(1L, 10000, PaymentType.CARD, PaymentStatus.PAID, now);

        assertThat(payment).isNotNull();
        assertThat(payment.getId()).isEqualTo(1L);
        assertThat(payment.getPrice()).isEqualTo(10000);
        assertThat(payment.getPaymentType()).isEqualTo(PaymentType.CARD);
        assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
        assertThat(payment.getCreatedAt()).isEqualTo(now);
    }
}