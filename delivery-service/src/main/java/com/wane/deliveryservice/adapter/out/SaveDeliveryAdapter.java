package com.wane.deliveryservice.adapter.out;

import com.wane.deliveryservice.adapter.out.persistence.DeliveryJpaEntityRepository;
import com.wane.deliveryservice.application.port.out.SaveDeliveryPort;
import com.wane.deliveryservice.domain.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SaveDeliveryAdapter implements SaveDeliveryPort {

    private final DeliveryMapper deliveryMapper;
    private final DeliveryJpaEntityRepository deliveryRepository;

    @Override
    public void saveDelivery(Delivery delivery) {
        deliveryRepository.save(deliveryMapper.toJpaEntity(delivery));
    }
}
