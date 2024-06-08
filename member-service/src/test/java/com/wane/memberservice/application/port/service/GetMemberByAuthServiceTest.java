package com.wane.memberservice.application.port.service;

import com.wane.memberservice.application.port.out.GetMemberByAuthIdAndOauthTypePort;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetMemberByAuthServiceTest {

    @InjectMocks
    private GetMemberByAuthService getMemberByAuthService;

    @Mock
    private GetMemberByAuthIdAndOauthTypePort getMemberByAuthIdAndOauthTypePort;

    @Test
    public void getMemberIdByAuthOrElseZeroReturnsMemberIdWhenMemberExists() {
        // given
        Member member = Member.create(1L, "", "", "", "", 0, null, null, "", null);

        given(getMemberByAuthIdAndOauthTypePort.getUserIdByOauthTypeAndAuthIdOrElseZero(any(AuthServiceType.class), anyString()))
                .willReturn(member);

        // when
        Long memberId = getMemberByAuthService.getMemberIdByAuthOrElseZero(AuthServiceType.KAKAO, "authId");

        // then
        Assertions.assertThat(memberId).isEqualTo(1L);
    }

    @Test
    public void getMemberIdByAuthOrElseZeroReturnsZeroWhenMemberDoesNotExist() {
        // given
        given(getMemberByAuthIdAndOauthTypePort.getUserIdByOauthTypeAndAuthIdOrElseZero(any(AuthServiceType.class), anyString()))
                .willReturn(null);

        // when
        Long memberId = getMemberByAuthService.getMemberIdByAuthOrElseZero(AuthServiceType.KAKAO, "authId");

        // then
        Assertions.assertThat(memberId).isEqualTo(0);
    }
}