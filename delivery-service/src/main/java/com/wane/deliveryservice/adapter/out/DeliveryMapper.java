package com.wane.deliveryservice.adapter.out;

import com.wane.deliveryservice.adapter.out.persistence.AddressJpaEntity;
import com.wane.deliveryservice.adapter.out.persistence.DeliveryJpaEntity;
import com.wane.deliveryservice.domain.Address;
import com.wane.deliveryservice.domain.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

    public DeliveryJpaEntity toJpaEntity(Delivery delivery) {
        return DeliveryJpaEntity.builder()
                .id(delivery.getId())
                .memberId(delivery.getMemberId())
                .orderId(delivery.getOrderId())
                .invoiceNumber(delivery.getInvoiceNumber())
                .deliveryStatus(delivery.getDeliveryStatus())
                .address(toJpaEntity(delivery.getAddress()))
                .build();
    }

    public AddressJpaEntity toJpaEntity(Address address) {
        return AddressJpaEntity.builder()
                .zipCode(address.getZipCode())
                .roadName(address.getRoadName())
                .detail(address.getDetail())
                .phoneNumber(address.getPhoneNumber())
                .recipient(address.getRecipient())
                .build();
    }
}
