package com.wane.memberservice.domain;

import lombok.Getter;

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

	private Member(Long id, String name, String email, String password, String phoneNumber, int point, AuthServiceType authServiceType, MemberRole role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.point = point;
		this.authServiceType = authServiceType;
		this.role = role;
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

	public static Member of(Long id, String name, String email, String password, String phoneNumber, int point, AuthServiceType authServiceType, MemberRole role) {
		return new Member(id, name,email, password, phoneNumber, point, authServiceType, role);
	}

	public static Member createUser(String name, String email, String password, String phoneNumber, AuthServiceType authServiceType) {
		if (authServiceType == AuthServiceType.NONE) {
			throw new IllegalArgumentException("회원은 oauth type이 있어야 합니다.");
		}
		return new Member(name,email, password, phoneNumber, 0, authServiceType, MemberRole.USER);
	}


	public static Member createAdminMember(String name, String email, String password, String phoneNumber) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("password는 필수 값 입니다.");
		}
		return new Member(name, email, password, phoneNumber, 0, AuthServiceType.NONE, MemberRole.ADMIN);
	}


}
