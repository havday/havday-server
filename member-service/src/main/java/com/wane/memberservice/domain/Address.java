package com.wane.memberservice.domain;

import lombok.Getter;

@Getter
public class Address {

	private Long id;
	private String name;
	private String zipCode;
	private String roadName;
	private String detail;
	private String phoneNumber;
	private String recipient; //수령인
	private boolean isBaseAddress;

	private Address(Long id, String name, String zipCode, String roadName, String detail, String phoneNumber, String recipient, boolean isBaseAddress) {
		this.id = id;
		this.name = name;
		this.zipCode = zipCode;
		this.roadName = roadName;
		this.detail = detail;
		this.phoneNumber = phoneNumber;
		this.recipient = recipient;
		this.isBaseAddress = isBaseAddress;
	}

	public static Address create(Long id,String name, String zipCode, String roadName, String detail, String phoneNumber, String recipient, boolean isBaseAddress) {
		return new Address(id, name, zipCode, roadName, detail, phoneNumber, recipient, isBaseAddress);
	}

	public void setBaseAddress(boolean isBaseAddress) {
		this.isBaseAddress = isBaseAddress;
	}

}
