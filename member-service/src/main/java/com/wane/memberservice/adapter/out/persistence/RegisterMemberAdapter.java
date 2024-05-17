package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.application.port.out.RegisterMemberPort;
import com.wane.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RegisterMemberAdapter implements RegisterMemberPort {

	private final MemberJpaEntityRepository repository;
	private final MemberMapper memberMapper;

	@Override
	public Member registerMember(Member member) {
		MemberJpaEntity savedMember = repository.save(
				new MemberJpaEntity(
						member.getEmail(),
						member.getRole()
				)
		);

		return memberMapper.toDomainEntity(savedMember);
	}
}
