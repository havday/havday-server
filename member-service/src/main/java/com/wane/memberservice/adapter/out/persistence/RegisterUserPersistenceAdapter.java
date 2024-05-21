package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.application.port.out.RegisterUserPort;
import com.wane.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RegisterUserPersistenceAdapter implements RegisterUserPort {

	private final MemberJpaEntityRepository repository;
	private final MemberMapper memberMapper;

	@Override
	public Member registerUser(Member member) {
		MemberJpaEntity savedMember = repository.save(
				new MemberJpaEntity(
						member.getName(),
						member.getEmail(),
						member.getPassword(),
						member.getPhoneNumber(),
						member.getPoint(),
						member.getAuthServiceType(),
						member.getRole(),
						member.getAuthId()
				)
		);

		return memberMapper.toDomainEntity(savedMember);
	}
}
