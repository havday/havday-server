package com.wane.memberservice.adapter.out.persistence.jpa;

import com.wane.memberservice.domain.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
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
		MemberJpaEntity memberEntity1 = new MemberJpaEntity("email", MemberRole.USER);
		MemberJpaEntity memberEntity2 = new MemberJpaEntity("email2", MemberRole.USER);
		repository.saveAll(List.of(memberEntity1, memberEntity2));

		//when
		Optional<MemberJpaEntity> optionalMemberEntity = repository.findByEmail("email");

		//then
		assertThat(optionalMemberEntity).isPresent();
	}


}