package com.wane.memberservice.adapter.out.persistence;

import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import com.wane.memberservice.IntegrationTestSupport;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.Member;
import com.wane.memberservice.domain.MemberRole;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class GetMemberPersistenceAdapterTest extends IntegrationTestSupport {

    @Autowired
    private GetMemberPersistenceAdapter getMemberAdapter;

    @Autowired
    private MemberJpaEntityRepository memberRepository;

    @DisplayName("회원이 존재하면 회원을 반환한다.")
    @Test
    void getUserWhenMemberExists() {
        //given
        MemberJpaEntity savedMember = saveAndReturnMember();

        //when
        Member member = getMemberAdapter.getUser(savedMember.getId());

        //then
        Assertions.assertThat(member).isNotNull();
    }

    @DisplayName("회원이 존재하지 않으면 CustomException 을 반환한다.")
    @Test
    void getUserWhenMemberNotExists() {
        //given
        Long memberId = 2L;

        //when & then
        Assertions.assertThatThrownBy(() -> getMemberAdapter.getUser(memberId))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.MEMBER_NOT_MATCH.getMessage());
    }

    private MemberJpaEntity saveAndReturnMember() {
        MemberJpaEntity member = new MemberJpaEntity(1L, "박재완", "wan2daaa@gmail.com", "1234", "phoneNumber", 10000, AuthServiceType.KAKAO, MemberRole.USER, null, "");

        return memberRepository.save(member);
    }
}