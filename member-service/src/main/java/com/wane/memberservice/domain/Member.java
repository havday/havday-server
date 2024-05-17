package com.wane.memberservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Member {

	private Long id;

	private String email;

	private MemberRole role;


	private Member(Long id, String email, MemberRole role) {
		this.id = id;
		this.email = email;
		this.role = role;
	}

	private Member(String email, MemberRole role) {
		this(null, email, role);
	}


	public static Member of(Long id , String email, MemberRole role) {
		return new Member(id, email, role);
	}

	public static Member createUserMember(String email) {
		return new Member(email, MemberRole.USER);
	}

	public static Member createAdminMember(String email) {
		return new Member(email, MemberRole.ADMIN);
	}


}
