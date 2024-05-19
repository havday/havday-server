package com.wane.memberservice.application.port.in;

import com.wane.memberservice.domain.AuthServiceType;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Getter
class RegisterUserCommandTest {

	@DisplayName("이름이 비어있으면 예외를 던진다.")
	@Test
	void throwExceptionWhenNameIsEmpty() {
		assertThatThrownBy(() -> new RegisterUserCommand("", "test@email.com", "password", "01012341234", AuthServiceType.KAKAO))
				.isInstanceOf(ConstraintViolationException.class);
		assertThatThrownBy(() -> new RegisterUserCommand("", "test@email.com", "password", "01012341234", AuthServiceType.KAKAO))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@DisplayName("이메일이 비어있으면 예외를 던진다.")
	@Test
	void throwExceptionWhenEmailIsEmpty() {
		assertThatThrownBy(() -> new RegisterUserCommand("이름","", "password", "01012341234", AuthServiceType.KAKAO))
				.isInstanceOf(ConstraintViolationException.class);
		assertThatThrownBy(() -> new RegisterUserCommand("이름",null, "password", "01012341234", AuthServiceType.KAKAO))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@DisplayName("휴대폰 번호가 비어있으면 예외를 던진다.")
	@Test
	void throwExceptionWhenPhoneNumberIsEmpty() {
		assertThatThrownBy(() -> new RegisterUserCommand("이름","test@email.com", "password", "", AuthServiceType.KAKAO))
				.isInstanceOf(ConstraintViolationException.class);
		assertThatThrownBy(() -> new RegisterUserCommand("이름","test@email.com", "password", null, AuthServiceType.KAKAO))
				.isInstanceOf(ConstraintViolationException.class);
	}

	@DisplayName("어떤 oauth 에서 인증한지에 대한 정보가 없으면 에외를 던진다.")
	@Test
	void throwExceptionWhenAuthServiceTypeIsNoneOrNull() {
		assertThatThrownBy(() -> new RegisterUserCommand("이름","test@email.com", "password", "01012341234", AuthServiceType.NONE))
				.isInstanceOf(IllegalArgumentException.class);

		assertThatThrownBy(() -> new RegisterUserCommand("이름","test@email.com", "password", "01012341234", null))
				.isInstanceOf(ConstraintViolationException.class);
	}



}