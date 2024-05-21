package com.wane.memberservice.adapter.out.persistence.jpa;

import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberJpaEntityRepositoryTest {

	@Autowired
	private MemberJpaEntityRepository repository;

	@DisplayName("email로 member entity를 찾을 수 있다.")
	@Test
	void findByEmail() {
		//given
		String findEmail = "email";

		MemberJpaEntity memberEntity = createUserWithEmail(findEmail);
		repository.save(memberEntity);

		//when
		Optional<MemberJpaEntity> optionalMemberEntity = repository.findByEmailAndMemberRole(findEmail, MemberRole.USER);

		//then
		assertThat(optionalMemberEntity).isPresent();
	}

	private MemberJpaEntity createUserWithEmail(String email) {
				return new MemberJpaEntity(
				"name",
				email,
				"",
				"01012341234",
				0,
				AuthServiceType.KAKAO,
				MemberRole.USER,
						"authId"
		);
	}

}