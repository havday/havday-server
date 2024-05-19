package com.wane.memberservice.adapter.out.persistence.jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "address")
public class AddressJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String zipCode;

	@Column(nullable = false)
	private String roadName;

	@Column(nullable = false)
	private String detail;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String recipient; //수령인

	@Column(nullable = false)
	private boolean isBaseAddress;

	public AddressJpaEntity(String name, String zipCode, String roadName, String detail, String phoneNumber, String recipient, boolean isBaseAddress) {
		this.name = name;
		this.zipCode = zipCode;
		this.roadName = roadName;
		this.detail = detail;
		this.phoneNumber = phoneNumber;
		this.recipient = recipient;
		this.isBaseAddress = isBaseAddress;
	}
}
