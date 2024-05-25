package com.wane.deliveryservice.application.port.out;

public record MemberAddress(
        String zipCode,
        String roadName,
        String detail,
        String phoneNumber,
        String recipient
) {
}
