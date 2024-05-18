package com.wane.memberservice.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

	@DisplayName("회원은 User 역할을 가질 수 있다.")
	@Test
	void createUser() {
		//given
		Member sut = Member.createUser(
				"이름",
				"test@email.com",
				"password",
				"01012341234",
				AuthServiceType.KAKAO
		);

		//when
		//then
		assertThat(sut.getRole()).isEqualTo(MemberRole.USER);
		assertThat(sut.getAuthServiceType()).isNotEqualTo(AuthServiceType.NONE);
	}


	@DisplayName("유저는 authType이 NONE 이면 안된다.")
	@Test
	void createUserWithNone() {
		assertThatThrownBy(() -> Member.createUser(
				"이름",
				"admin@email.com",
				"password",
				"01012341234",
				AuthServiceType.NONE
		))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("회원은 oauth type이 있어야 합니다.");
	}

	@DisplayName("회원은 Admin 역할을 가질 수 있다.")
	@Test
	void createAdminMember() {
		//given
		Member sut = Member.createAdminMember(
				"이름",
				"admin@email.com",
				"password",
				"01012341234");

		//when
		//then
		assertThat(sut.getRole()).isEqualTo(MemberRole.ADMIN);
		assertThat(sut.getAuthServiceType()).isEqualTo(AuthServiceType.NONE);
	}

	@DisplayName("관리자는 비밀번호가 비면 안된다.")
	@Test
	void createAdminMemberWithoutPassword() throws Exception {
		assertThatThrownBy(() -> Member.createAdminMember(
				"이름",
				"admin@email.com",
				"",
				"01012341234"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("password는 필수 값 입니다.");
	}

}