package com.wane.orderservice.application.port.out;

public record CreateDeliveryCommand(Long memberId, Long addressId, Long orderId) {}
