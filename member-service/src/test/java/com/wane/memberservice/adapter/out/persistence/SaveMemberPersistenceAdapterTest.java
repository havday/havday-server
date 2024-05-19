package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.IntegrationTestSupport;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class SaveMemberPersistenceAdapterTest extends IntegrationTestSupport {

	@Autowired
	private SaveMemberPersistenceAdapter saveMemberPersistenceAdapter;

	@Autowired
	private MemberJpaEntityRepository memberRepository;

	@Autowired
	private MemberMapper memberMapper;

	@DisplayName("멤버를 저장하고 도메인 멤버 객체로 변환하여 반환한다.")
	@Test
	void saveMember() {
		//given
		Member member = Member.createUser("이름", "email", "password", "01012341234", AuthServiceType.KAKAO);
		member.addAddress("재완집", "01233", "도로명 주소", "상세 주소", "01012341234", "수취인", false);
		member.addAddress("남의집", "12300", "도로명 주소2", "상세 주소2", "01012311234", "수취인2", true);

		//when
		Member sut = saveMemberPersistenceAdapter.saveMember(member);

		//then
		assertThat(sut.getId()).isNotNull();

		assertThat(sut.getAddresses()).hasSize(2);
	}


}