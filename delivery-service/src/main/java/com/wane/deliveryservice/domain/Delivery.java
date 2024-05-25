package com.wane.deliveryservice.domain;

import lombok.Getter;

@Getter
public class Delivery {

    private Long id;
    private Long memberId;
    private Long orderId;
    private String invoiceNumber;
    private DeliveryStatus deliveryStatus;
    private Address address;

    public static Delivery create(Long memberId,
                                  Long orderId,
                                  String zipCode,
                                  String roadName,
                                  String detail,
                                  String phoneNumber,
                                  String recipient
    ) {
        Address address = Address.create(zipCode, roadName, detail, phoneNumber, recipient);

        return new Delivery(null, memberId, orderId, "", DeliveryStatus.REQUEST, address);
    }

    private Delivery(Long id, Long memberId, Long orderId, String invoiceNumber, DeliveryStatus deliveryStatus, Address address) {
        validateDeliveryRequiredFields(memberId, orderId, invoiceNumber, deliveryStatus, address);
        this.id = id;
        this.memberId = memberId;
        this.orderId = orderId;
        this.invoiceNumber = invoiceNumber;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
    }

    private void validateDeliveryRequiredFields(Long memberId, Long orderId, String invoiceNumber, DeliveryStatus deliveryStatus, Address address) {
        if (memberId == null || orderId == null || deliveryStatus == null || address == null) {
            throw new IllegalArgumentException("배송 정보는 필수 값 입니다.");
        }
    }


}
