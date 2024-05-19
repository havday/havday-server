package com.wane.memberservice.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Member {

	private Long id;

	private String name;

	private String email;

	private String password;

	private String phoneNumber;

	private int point;

	private AuthServiceType authServiceType;

	private MemberRole role;

	private List<Address> addresses = new ArrayList<>();

	private Member(Long id, String name, String email, String password, String phoneNumber, int point, AuthServiceType authServiceType, MemberRole role, List<Address> addresses) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.point = point;
		this.authServiceType = authServiceType;
		this.role = role;
		this.addresses = addresses;
	}

	private Member(String name, String email, String password, String phoneNumber, int point, AuthServiceType authServiceType, MemberRole role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.point = point;
		this.authServiceType = authServiceType;
		this.role = role;
	}

	public static Member create(Long id, String name, String email, String password, String phoneNumber, int point, AuthServiceType authServiceType, MemberRole role, List<Address> addresses) {
		return new Member(id, name, email, password, phoneNumber, point, authServiceType, role, addresses);
	}

	public static Member createUser(String name, String email, String password, String phoneNumber, AuthServiceType authServiceType) {
		if (authServiceType == AuthServiceType.NONE) {
			throw new IllegalArgumentException("회원은 oauth type이 있어야 합니다.");
		}
		return new Member(name, email, password, phoneNumber, 0, authServiceType, MemberRole.USER);
	}


	public static Member createAdminMember(String name, String email, String password, String phoneNumber) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("password는 필수 값 입니다.");
		}
		return new Member(name, email, password, phoneNumber, 0, AuthServiceType.NONE, MemberRole.ADMIN);
	}

	public void addAddress(
			String name,
			String zipCode,
			String roadName,
			String detail,
			String phoneNumber,
			String recipient,
			boolean isBaseAddress
	) {
		if (addresses.isEmpty()) {
			Address newAddress = Address.create(null, name, zipCode, roadName, detail, phoneNumber, recipient, true);
			this.addresses.add(newAddress);
			return;
		}

		boolean hasBaseAddress = addresses.stream().map(Address::isBaseAddress).toList().contains(true);

		Address newAddress = Address.create(null, name, zipCode, roadName, detail, phoneNumber, recipient, isBaseAddress);


		if (hasBaseAddress && isBaseAddress) {
			for (Address address : this.addresses) {
				address.setBaseAddress(false);
			}
		}

		this.addresses.add(newAddress);
	}

}
