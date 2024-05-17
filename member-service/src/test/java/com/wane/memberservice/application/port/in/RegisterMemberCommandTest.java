package com.wane.memberservice.application.port.in;

import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RegisterMemberCommandTest {

	@DisplayName("RegisterMemberCommand는 email이 null이거나 빈 문자열이면 예외를 반환한다.")
	@Test
	void registerMemberCommand() {
		assertThatThrownBy(() -> new RegisterMemberCommand(""))
				.isInstanceOf(ConstraintViolationException.class);
		assertThatThrownBy(() -> new RegisterMemberCommand(null))
				.isInstanceOf(ConstraintViolationException.class);
	}

}