package com.wane.orderservice.application.port.out;

public interface CreateDeliveryPort {
  void createDelivery(CreateDeliveryCommand command);
}
