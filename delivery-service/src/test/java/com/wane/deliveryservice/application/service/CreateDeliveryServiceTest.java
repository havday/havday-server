package com.wane.deliveryservice.application.service;

import com.wane.deliveryservice.application.port.out.FindMemberAddressPort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import com.wane.deliveryservice.application.port.in.CreateDeliveryCommand;
import com.wane.deliveryservice.application.port.out.FindMemberAddressPort;
import com.wane.deliveryservice.application.port.out.MemberAddress;
import com.wane.deliveryservice.application.port.out.SaveDeliveryPort;
import com.wane.deliveryservice.domain.Delivery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateDeliveryServiceTest {

    @Mock
    private FindMemberAddressPort findMemberAddressPort;

    @Mock
    private SaveDeliveryPort saveDeliveryPort;

    @InjectMocks
    private CreateDeliveryService createDeliveryService;

    @Test
    public void testCreateDelivery() {
        // Given
        MemberAddress memberAddress = new MemberAddress("12345", "RoadName", "Detail", "PhoneNumber", "Recipient");
        when(findMemberAddressPort.findMemberAddress(any(), any())).thenReturn(memberAddress);

        CreateDeliveryCommand command = new CreateDeliveryCommand(1L, 1L, "OrderId");

        // When
        createDeliveryService.createDelivery(command);

        // Then
        verify(findMemberAddressPort).findMemberAddress(command.getMemberId(), command.getAddressId());
        verify(saveDeliveryPort).saveDelivery(any(Delivery.class));
    }
}