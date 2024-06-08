package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.Member;
import com.wane.memberservice.domain.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class GetMemberByAuthAndOauthTypeAdapterTest {

    @Autowired
    private GetMemberByAuthAndOauthTypeAdapter getMemberAdapter;

    @Autowired
    private MemberJpaEntityRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;

    @DisplayName("회원이 존재하면 회원을 반환한다.")
    @Test
    void getUserIdByOauthTypeAndAuthIdOrElseZero() {
        //given
        AuthServiceType authServiceType = AuthServiceType.KAKAO;
        String authId = "authId";

        saveMemberWithAuthServiceTypeAndAuthId(authServiceType,authId);

        //when
        Member member = getMemberAdapter.getUserIdByOauthTypeAndAuthIdOrElseZero(authServiceType, authId);

        //then
        assertNotNull(member);
    }

    @DisplayName("회원이 존재하지 않으면 null 을 반환한다.")
    @Test
    void getUserIdByOauthTypeAndAuthIdOrElseZeroWhenMemberNotExists() {
        //given
        AuthServiceType authServiceType = AuthServiceType.KAKAO;
        String authId = "notExistsAuthId";

        //when
        Member member = getMemberAdapter.getUserIdByOauthTypeAndAuthIdOrElseZero(authServiceType, authId);

        //then
        assertNull(member);
    }

    private void saveMemberWithAuthServiceTypeAndAuthId(AuthServiceType authServiceType, String authId) {
        MemberJpaEntity member = new MemberJpaEntity(1L, "박재완", "wan2daaa@gmail.com", "1234", "phoneNumber", 10000, authServiceType, MemberRole.USER, null, authId);

        memberRepository.save(member);
    }
}
