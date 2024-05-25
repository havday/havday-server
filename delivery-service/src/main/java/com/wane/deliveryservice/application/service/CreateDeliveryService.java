package com.wane.deliveryservice.application.service;

import com.wane.deliveryservice.application.port.in.CreateDeliveryCommand;
import com.wane.deliveryservice.application.port.in.CreateDeliveryUseCase;
import com.wane.deliveryservice.application.port.out.FindMemberAddressPort;
import com.wane.deliveryservice.application.port.out.MemberAddress;
import com.wane.deliveryservice.application.port.out.SaveDeliveryPort;
import com.wane.deliveryservice.domain.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateDeliveryService implements CreateDeliveryUseCase {

    private final FindMemberAddressPort findMemberAddressPort;
    private final SaveDeliveryPort saveDeliveryPort;

    @Override
    public void createDelivery(CreateDeliveryCommand command) {
        MemberAddress memberAddress = findMemberAddressPort.findMemberAddress(command.getMemberId(), command.getAddressId());

        Delivery delivery = Delivery.create(
                command.getMemberId(),
                command.getOrderId(),
                memberAddress.zipCode(),
                memberAddress.roadName(),
                memberAddress.detail(),
                memberAddress.phoneNumber(),
                memberAddress.recipient()
        );

        saveDeliveryPort.saveDelivery(delivery);
    }
}
