package com.wane.deliveryservice.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "address")
public class AddressJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String zipCode;
    private String roadName;
    private String detail;
    private String phoneNumber;
    private String recipient;

    @Builder
    public AddressJpaEntity(Long id, String zipCode, String roadName, String detail, String phoneNumber, String recipient) {
        this.id = id;
        this.zipCode = zipCode;
        this.roadName = roadName;
        this.detail = detail;
        this.phoneNumber = phoneNumber;
        this.recipient = recipient;
    }
}
