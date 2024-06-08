package com.wane.orderservice.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentTypeTest {

    @DisplayName("영문 이름으로 결제 타입을 찾을 수 있어야 합니다.")
    @Test
    void findByEnNameReturnsCorrectPaymentType() {
        assertThat(PaymentType.findByEnName("no-bankbook")).isEqualTo(PaymentType.NO_BANKBOOK);
        assertThat(PaymentType.findByEnName("card")).isEqualTo(PaymentType.CARD);
    }

    @DisplayName("지원하지 않는 결제 타입을 찾으려고 하면 예외가 발생해야 합니다.")
    @Test
    void findByEnNameThrowsExceptionForUnsupportedPaymentType() {
        assertThatThrownBy(() -> PaymentType.findByEnName("unsupported"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않은 결제 타입입니다.");
    }
}