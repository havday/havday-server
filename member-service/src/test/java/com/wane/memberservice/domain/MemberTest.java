package com.wane.memberservice.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

	@DisplayName("회원은 User 역할을 가질 수 있다.")
	@Test
	void createUserMember() {
		//given
		Member sut = Member.createUserMember("test@email.com");

		//when
		MemberRole sutRole = sut.getRole();

		//then
		assertThat(sutRole).isEqualTo(MemberRole.USER);
	}

	@DisplayName("회원은 Admin 역할을 가질 수 있다.")
	@Test
	void createAdminMember() {
		//given
		Member sut = Member.createAdminMember("test@email.com");

		//when
		MemberRole sutRole = sut.getRole();

		//then
		assertThat(sutRole).isEqualTo(MemberRole.ADMIN);
	}


}