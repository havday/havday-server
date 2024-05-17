package com.wane.memberservice.application.port.service;

import com.wane.memberservice.application.port.in.RegisterMemberCommand;
import com.wane.memberservice.application.port.in.RegisterMemberUseCase;
import com.wane.memberservice.application.port.out.ExistMemberPort;
import com.wane.memberservice.application.port.out.RegisterMemberPort;
import com.wane.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegisterMemberService implements RegisterMemberUseCase {

	private final RegisterMemberPort registerMemberPort;
	private final ExistMemberPort existMemberPort;

	@Transactional
	@Override
	public void registerMember(RegisterMemberCommand command) {

		boolean isMemberExists = existMemberPort.existMemberByEmail(command.email());

		if (isMemberExists) {
			throw new IllegalStateException("Member already exists");
		}

		Member member = Member.createUserMember(command.email());
		registerMemberPort.registerMember(member);
	}
}
