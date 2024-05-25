package com.wane.deliveryservice.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DeliveryStatus {
    REQUEST("배송 요청"),
    IN_PROGRESS("배송 중"),
    COMPLETED("배송 완료"),
    CANCELED("배송 취소"),
    RETURNED("반품");

    private final String name;
}
