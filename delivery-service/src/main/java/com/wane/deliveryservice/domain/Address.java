package com.wane.deliveryservice.domain;

import lombok.Getter;

@Getter
public class Address {

    private String zipCode;
    private String roadName;
    private String detail;
    private String phoneNumber;
    private String recipient;

    private Address(String zipCode, String roadName, String detail, String phoneNumber, String recipient) {
        validateAddressRequiredFields(zipCode, roadName, detail, phoneNumber, recipient);

        this.zipCode = zipCode;
        this.roadName = roadName;
        this.detail = detail;
        this.phoneNumber = phoneNumber;
        this.recipient = recipient;
    }

    private static void validateAddressRequiredFields(String zipCode, String roadName, String detail, String phoneNumber, String recipient) {
        if (zipCode.isEmpty() || roadName.isEmpty() || detail.isEmpty() || phoneNumber.isEmpty() || recipient.isEmpty()) {
            throw new IllegalArgumentException("주소 정보는 필수 값입니다.");
        }
    }

    public static Address create(String zipCode, String roadName, String detail, String phoneNumber, String recipient) {
        return new Address(zipCode, roadName, detail, phoneNumber, recipient);
    }
}
