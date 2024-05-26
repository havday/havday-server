package com.wane.orderservice.adapter.in.web.dto.response;

import java.time.LocalDate;

public record CreateOrderResponse(
        String orderId,
        LocalDate createdDate
) {
}
