package com.wane.memberservice.application.port.in;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SaveAddressCommandTest {

	@DisplayName("회원 id는 필수 값 이다.")
	@Test
	void throwExceptionWhenMemberIdIsEmpty() {
		assertThatThrownBy(() -> new SaveAddressCommand(null, "재완집", "test", "12345", "test road", "test detail", "01012341234", true))
				.isInstanceOf(ConstraintViolationException.class)
				.hasMessageContaining("member id는 필수 값 입니다.");
	}

	@DisplayName("배송지명은 필수 값 이다.")
	@Test
	void throwExceptionWhenNameIsEmpty() {
		assertThatThrownBy(() -> new SaveAddressCommand(1L, "", "test", "12345", "test road", "test detail", "01012341234", true))
				.isInstanceOf(ConstraintViolationException.class)
				.hasMessageContaining("배송지명는 필수 값 입니다.");
	}

	@DisplayName("수취인은 필수 값 이다.")
	@Test
	void throwExceptionWhenRecipientIsEmpty() {
		assertThatThrownBy(() -> new SaveAddressCommand(1L, "재완집", "", "12345", "test road", "test detail", "01012341234", true))
				.isInstanceOf(ConstraintViolationException.class)
				.hasMessageContaining("수취인은 필수 값 입니다.");
	}

	@DisplayName("우편번호는 필수 값 이다.")
	@Test
	void throwExceptionWhenZipCodeIsEmpty() {
		assertThatThrownBy(() -> new SaveAddressCommand(1L, "재완집", "박재완", "", "test road", "test detail", "01012341234", true))
				.isInstanceOf(ConstraintViolationException.class)
				.hasMessageContaining("우편번호는 필수 값 입니다.");
	}

	@DisplayName("도로명 주소는 필수 값 이다.")
	@Test
	void throwExceptionWhenRoadNameIsEmpty() {
		assertThatThrownBy(() -> new SaveAddressCommand(1L, "재완집", "박재완", "12345", "", "test detail", "01012341234", true))
				.isInstanceOf(ConstraintViolationException.class)
				.hasMessageContaining("도로명 주소는 필수 값 입니다.");
	}

	@DisplayName("수취인 휴대번호는 필수 값 이다.")
	@Test
	void throwExceptionWhenPhoneNumberIsEmpty() {
		assertThatThrownBy(() -> new SaveAddressCommand(1L, "재완집", "박재완", "12345", "test road", "test detail", "", true))
				.isInstanceOf(ConstraintViolationException.class)
				.hasMessageContaining("수취인 휴대번호는 필수 값 입니다.");
	}

	@DisplayName("기본 배송지 유무는 필수 값 이다.")
	@Test
	void throwExceptionWhenIsBaseAddressIsEmpty() {
		assertThatThrownBy(() -> new SaveAddressCommand(1L, "재완집", "박재완", "12345", "test road", "test detail", "01012341234", null))
				.isInstanceOf(ConstraintViolationException.class)
				.hasMessageContaining("기본 배송지 유무는 필수 값 입니다.");
	}

}