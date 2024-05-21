package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class GetUserByAuthAndOauthTypePort implements com.wane.memberservice.application.port.out.GetUserByAuthIdAndOauthTypePort {

	private final MemberJpaEntityRepository memberRepository;
	private final MemberMapper memberMapper;

	@Override
	public Member getUserIdByOauthTypeAndAuthIdOrElseZero(AuthServiceType oauthType, String oauthId) {
		return memberRepository.findByAuthServiceTypeAndAuthId(oauthType, oauthId)
				.map(memberMapper::toDomainEntity)
				.orElse(null);
	}
}
