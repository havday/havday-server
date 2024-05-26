package com.wane.orderservice.application.port.in;

import com.wane.orderservice.domain.Order;

public interface CreateOrderUseCase {

	Order createOrder(CreateOrderCommand command);
}
