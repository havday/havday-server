package com.wane.memberservice.application.port.service;

import com.wane.memberservice.application.port.in.RegisterUserCommand;
import com.wane.memberservice.application.port.in.RegisterUserUseCase;
import com.wane.memberservice.application.port.out.ExistUserPort;
import com.wane.memberservice.application.port.out.RegisterUserPort;
import com.wane.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegisterUserService implements RegisterUserUseCase {

	private final RegisterUserPort registerUserPort;
	private final ExistUserPort existUserPort;

	@Transactional
	@Override
	public void registerUser(RegisterUserCommand command) {

		boolean isUserExists = existUserPort.existUserByEmail(command.getEmail());

		if (isUserExists) {
			throw new IllegalStateException("이미 가입된 회원 입니다.");
		}

		Member member = Member.createUser(
				command.getName(),
				command.getEmail(),
				command.getPassword(),
				command.getPhoneNumber(),
				command.getAuthServiceType()
		);
		registerUserPort.registerUser(member);
	}
}
