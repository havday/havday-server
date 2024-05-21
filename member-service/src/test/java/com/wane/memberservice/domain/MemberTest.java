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
				"user@email.com",
				"password",
				"01012341234",
				AuthServiceType.KAKAO,
				"authId"
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
				"user@email.com",
				"password",
				"01012341234",
				AuthServiceType.NONE,
				"authId"
		))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("회원은 oauth type이 있어야 합니다.");
	}

	@DisplayName("유저는 authId가 필수 값이다.")
	@Test
	void createUserWithEmptyAuthId() {
		assertThatThrownBy(() -> Member.createUser(
				"이름",
				"user@email.com",
				"password",
				"01012341234",
				AuthServiceType.KAKAO,
				""
		))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("authId는 필수 값 입니다.");
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

	@DisplayName("주소를 추가할 수 있다.")
	@Test
	void addAddress() {
	    //given
		Member user = Member.createUser("이름", "email", "", "01012341234", AuthServiceType.KAKAO, "authId");

		//when
		user.addAddress("와니집", "12345", "분포로 114", "엘지메트로시티", "01023452345", "정경주", true);

	    //then
		assertThat(user.getAddresses()).hasSize(1);
		assertThat(user.getAddresses().getFirst().getName()).isEqualTo("와니집");
		assertThat(user.getAddresses().getFirst().getZipCode()).isEqualTo("12345");
		assertThat(user.getAddresses().getFirst().getRoadName()).isEqualTo("분포로 114");
		assertThat(user.getAddresses().getFirst().getDetail()).isEqualTo("엘지메트로시티");
		assertThat(user.getAddresses().getFirst().getPhoneNumber()).isEqualTo("01023452345");
		assertThat(user.getAddresses().getFirst().getRecipient()).isEqualTo("정경주");
		assertThat(user.getAddresses().getFirst().isBaseAddress()).isTrue();
	}

	@DisplayName("첫번째로 추가되는 주소는 무조건 기본 배송지로 설정된다.")
	@Test
	void firstAddressIsBaseAddress() {
	    //given
		Member user = Member.createUser("이름", "email", "", "01012341234", AuthServiceType.KAKAO, "authId");

		//when
		user.addAddress("와니집", "12345", "분포로 114", "엘지메트로시티", "01023452345", "정경주", false);

	    //then
		assertThat(user.getAddresses()).hasSize(1);
		assertThat(user.getAddresses().getFirst().getName()).isEqualTo("와니집");
		assertThat(user.getAddresses().getFirst().getZipCode()).isEqualTo("12345");
		assertThat(user.getAddresses().getFirst().getRoadName()).isEqualTo("분포로 114");
		assertThat(user.getAddresses().getFirst().getDetail()).isEqualTo("엘지메트로시티");
		assertThat(user.getAddresses().getFirst().getPhoneNumber()).isEqualTo("01023452345");
		assertThat(user.getAddresses().getFirst().getRecipient()).isEqualTo("정경주");
		assertThat(user.getAddresses().getFirst().isBaseAddress()).isTrue();
	}

	@DisplayName("기존 기본 주소지가 있어도 새로 기본주소지를 설정하면 기존 기본주소지는 일반 주소지가 된다.")
	@Test
	void lastAddressBecomeBaseAddress() {
		//given
		Member user = Member.createUser("이름", "email", "", "01012341234", AuthServiceType.KAKAO, "authId");
		user.addAddress("기존기본주소", "12345", "분포로 114", "엘지메트로시티", "01023452345", "정경주", true);
		//when
		user.addAddress("새로운기본주소", "12345", "분포로 114", "엘지메트로시티", "01023452345", "정경주", true);

		//then
		assertThat(user.getAddresses()).hasSize(2);
		assertThat(user.getAddresses().get(0).isBaseAddress()).isFalse();
		assertThat(user.getAddresses().get(0).getName()).isEqualTo("기존기본주소");
		assertThat(user.getAddresses().get(1).isBaseAddress()).isTrue();
		assertThat(user.getAddresses().get(1).getName()).isEqualTo("새로운기본주소");
	}




}