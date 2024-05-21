package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.IntegrationTestSupport;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.Member;
import com.wane.memberservice.domain.MemberRole;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@Transactional
class GetUserPersistenceAdapterTest extends IntegrationTestSupport {

	@Autowired
	private GetUserPersistenceAdapter getUserPersistenceAdapter;

	@Autowired
	private MemberJpaEntityRepository memberRepository;

	@DisplayName("memberId 로 회원을 반환한다.")
	@Test
	void getUser() {
	    //given
		MemberJpaEntity memberJpaEntity = new MemberJpaEntity("이름", "email", "password", "01012341234", 1000, AuthServiceType.KAKAO, MemberRole.USER, "authId");
		memberRepository.save(memberJpaEntity);

	    //when
		Member sut = getUserPersistenceAdapter.getUser(memberJpaEntity.getId());

		//then
		assertThat(sut.getId()).isEqualTo(memberJpaEntity.getId());
		assertThat(sut.getName()).isEqualTo("이름");
		assertThat(sut.getEmail()).isEqualTo("email");
		assertThat(sut.getPassword()).isEqualTo("password");
		assertThat(sut.getPhoneNumber()).isEqualTo("01012341234");
		assertThat(sut.getPoint()).isEqualTo(1000);
		assertThat(sut.getAuthServiceType()).isEqualTo(AuthServiceType.KAKAO);
		assertThat(sut.getRole()).isEqualTo(MemberRole.USER);
		assertThat(sut.getAuthId()).isEqualTo("authId");
		assertThat(sut.getAddresses()).isEqualTo(new ArrayList<>());
	}

	@DisplayName("회원이 존재하지 않으면 JpaObjectRetrievalFailureException을 던진다.")
	@Test
	void test() {
	    //given
		//when
	    //then
		assertThatThrownBy(() -> getUserPersistenceAdapter.getUser(100L))
				.isInstanceOf(JpaObjectRetrievalFailureException.class)
				.hasMessage("Member not found");
	}


}