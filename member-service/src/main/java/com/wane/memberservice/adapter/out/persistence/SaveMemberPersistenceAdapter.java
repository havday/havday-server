package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.application.port.out.SaveMemberPort;
import com.wane.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SaveMemberPersistenceAdapter implements SaveMemberPort {

	private final MemberJpaEntityRepository memberRepository;
	private final MemberMapper memberMapper;

	@Override
	public Member saveMember(Member member) {
		MemberJpaEntity jpaEntity = memberMapper.toJpaEntity(member);
		memberRepository.save(jpaEntity);
		return memberMapper.toDomainEntity(jpaEntity);
	}
}
