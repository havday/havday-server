package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

	public Member toDomainEntity(MemberJpaEntity jpaEntity) {
		return Member.of(
				jpaEntity.getId(),
				jpaEntity.getEmail(),
				jpaEntity.getMemberRole()
		);
	}

}