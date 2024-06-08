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
class RegisterMemberPersistenceAdapterTest extends IntegrationTestSupport {

    @Autowired
    private RegisterMemberPersistenceAdapter registerMemberAdapter;

    @Autowired
    private MemberJpaEntityRepository memberRepository;


    @DisplayName("회원을 등록하고, id 를 담아서 반환한다.")
    @Test
    void registerUser() {
        //given
        Member member = Member.createUser("박재완", "test@email.com", "password", "01012341234", AuthServiceType.KAKAO, "authId");

        //when
        Member registeredMember = registerMemberAdapter.registerUser(member);

        //then
        assertThat(registeredMember.getId()).isNotNull();
        assertThat(registeredMember.getId()).isNotNull();
        assertThat(registeredMember.getName()).isEqualTo(member.getName());
        assertThat(registeredMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(registeredMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(registeredMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());
        assertThat(registeredMember.getPoint()).isEqualTo(member.getPoint());
        assertThat(registeredMember.getAuthServiceType()).isEqualTo(member.getAuthServiceType());
        assertThat(registeredMember.getRole()).isEqualTo(member.getRole());
        assertThat(registeredMember.getAuthId()).isEqualTo(member.getAuthId());

    }
}