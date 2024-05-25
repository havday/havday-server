package com.wane.deliveryservice.application.port.out;

import com.wane.deliveryservice.domain.Delivery;

public interface SaveDeliveryPort {

    void saveDelivery(Delivery delivery);
}
