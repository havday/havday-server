package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.IntegrationTestSupport;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
class ExistMemberAdapterTest extends IntegrationTestSupport {

	@Autowired
	private ExistMemberPersistenceAdapter existMemberAdapter;

	@Autowired
	private MemberJpaEntityRepository memberRepository;

	@DisplayName("member 가 존재하면 true 를 반환한다.")
	@Test
	void existMemberByEmail() {
		//given
		String email = "test@email.com";
		saveMemberEntityWithEmail(email);

		//when
		boolean isMemberExists = existMemberAdapter.existMemberByEmail(email);

		//then
		Assertions.assertThat(isMemberExists).isTrue();
	}


	@DisplayName("member 가 존재하지 않으면 false 를 반환한다.")
	@Test
	void existMemberByEmailWhenMemberNotExists() {
		//given
		String email = "test@email.com";
		String notExistsEmail = "notExists@email.com";
		saveMemberEntityWithEmail(email);

		//when
		boolean isMemberExists = existMemberAdapter.existMemberByEmail(notExistsEmail);

		//then
		Assertions.assertThat(isMemberExists).isFalse();
	}

	private void saveMemberEntityWithEmail(String email) {
		memberRepository.save(
				new MemberJpaEntity(
						"name",
						email,
						"",
						"01012341234",
						0,
						AuthServiceType.KAKAO,
						MemberRole.USER,
						"authId"
				)
		);
	}

}