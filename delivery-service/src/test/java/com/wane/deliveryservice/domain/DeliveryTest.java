package com.wane.deliveryservice.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryTest {

    @Test
    public void createDelivery_withValidParameters_shouldReturnDelivery() {
        // Given
        Long memberId = 1L;
        String orderId = "OrderId";
        String zipCode = "12345";
        String roadName = "RoadName";
        String detail = "Detail";
        String phoneNumber = "PhoneNumber";
        String recipient = "Recipient";

        // When
        Delivery delivery = Delivery.create(memberId, orderId, zipCode, roadName, detail, phoneNumber, recipient);

        // Then
        assertThat(delivery).isNotNull();
        assertThat(delivery).isNotNull();
        assertThat(memberId).isEqualTo(delivery.getMemberId());
        assertThat(orderId).isEqualTo(delivery.getOrderId());
        assertThat(DeliveryStatus.REQUEST).isEqualTo(delivery.getDeliveryStatus());
        assertThat(delivery.getAddress()).isNotNull();
    }

    @Test
    public void createDelivery_withNullMemberId_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                Delivery.create(null, "OrderId", "12345", "RoadName", "Detail", "PhoneNumber", "Recipient")
        );
    }

    @Test
    public void createDelivery_withNullOrderId_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                Delivery.create(1L, null, "12345", "RoadName", "Detail", "PhoneNumber", "Recipient")
        );
    }

    @Test
    public void createDelivery_withNullAddress_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                Delivery.create(1L, "OrderId", null, null, null, null, null)
        );
    }
}
