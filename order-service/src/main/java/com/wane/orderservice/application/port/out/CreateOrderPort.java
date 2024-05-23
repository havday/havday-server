package com.wane.orderservice.application.port.out;

import com.wane.orderservice.domain.Order;

public interface CreateOrderPort {

	Order createOrder(Order order);
}
