package com.wane.memberservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

	private Long id;

	private String email;

	private MemberRole role;

	public static Member createUserMember(Long id, String email) {
		return new Member(id, email, MemberRole.USER);
	}

	public static Member createAdminMember(Long id, String email) {
		return new Member(id, email, MemberRole.ADMIN);
	}


}
