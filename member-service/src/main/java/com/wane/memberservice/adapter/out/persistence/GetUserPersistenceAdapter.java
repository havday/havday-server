package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.application.port.out.GetUserPort;
import com.wane.memberservice.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class GetUserPersistenceAdapter implements GetUserPort {

	private final MemberJpaEntityRepository memberRepository;
	private final MemberMapper memberMapper;

	@Override
	public Member getUser(Long memberId) {
		MemberJpaEntity memberJpaEntity = memberRepository.findById(memberId)
				.orElseThrow(() -> new EntityNotFoundException("Member not found"));

		return memberMapper.toDomainEntity(memberJpaEntity);
	}
}
