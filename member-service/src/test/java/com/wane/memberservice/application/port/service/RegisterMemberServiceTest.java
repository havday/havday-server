package com.wane.memberservice.application.port.service;

import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import com.wane.memberservice.application.port.in.RegisterUserCommand;
import com.wane.memberservice.application.port.out.ExistMemberPort;
import com.wane.memberservice.application.port.out.RegisterMemberPort;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterMemberServiceTest {

    @InjectMocks
    private RegisterMemberService registerMemberService;

    @Mock
    private RegisterMemberPort registerMemberPort;

    @Mock
    private ExistMemberPort existMemberPort;


    @Test
    public void registerUserSuccessfullyWhenUserDoesNotExist() {
        // given
        RegisterUserCommand command = new RegisterUserCommand(
                "name",
                "email",
                "password",
                "phoneNumber",
                AuthServiceType.KAKAO,
                "authId"
        );
        given(existMemberPort.existMemberByEmail(anyString())).willReturn(false);

        // when
        registerMemberService.registerUser(command);

        //then
        verify(registerMemberPort, times(1)).registerUser(any(Member.class));
    }

    @Test
    public void throwExceptionWhenUserAlreadyExists() {
        // given
        RegisterUserCommand command = new RegisterUserCommand(
                "name",
                "email",
                "password",
                "phoneNumber",
                AuthServiceType.KAKAO,
                "authId"
        );
        given(existMemberPort.existMemberByEmail(anyString())).willReturn(true);

        //when & then
        Assertions.assertThatThrownBy(() -> registerMemberService.registerUser(command))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.MEMBER_ALREADY_REGISTERED.getMessage());
    }
}