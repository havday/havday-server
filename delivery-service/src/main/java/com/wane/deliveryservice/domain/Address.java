package com.wane.deliveryservice.domain;

import lombok.Getter;

@Getter
public class Address {

    private final String zipCode;
    private final String roadName;
    private final String detail;
    private final String phoneNumber;
    private final String recipient;

    public static Address create(String zipCode, String roadName, String detail, String phoneNumber, String recipient) {
        return new Address(zipCode, roadName, detail, phoneNumber, recipient);
    }

    private Address(String zipCode, String roadName, String detail, String phoneNumber, String recipient) {
        validateAddressRequiredFields(zipCode, roadName, detail, phoneNumber, recipient);

        this.zipCode = zipCode;
        this.roadName = roadName;
        this.detail = detail;
        this.phoneNumber = phoneNumber;
        this.recipient = recipient;
    }

    private void validateAddressRequiredFields(String zipCode, String roadName, String detail, String phoneNumber, String recipient) {
        if (zipCode == null || zipCode.isEmpty() ||
                roadName == null || roadName.isEmpty() ||
                detail == null || detail.isEmpty() ||
                phoneNumber == null || phoneNumber.isEmpty() ||
                recipient == null || recipient.isEmpty()) {
            throw new IllegalArgumentException("주소 정보는 필수 값입니다.");
        }
    }

}
