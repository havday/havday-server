package com.wane.memberservice.adapter.in.web.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.memberservice.application.port.in.GetMemberByAuthUseCase;
import com.wane.memberservice.domain.AuthServiceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(controllers = GetMemberByAuthController.class)
class GetMemberByAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetMemberByAuthUseCase getMemberByAuthUseCase;

    @DisplayName("회원 인증 정보로 회원을 조회한다")
    @Test
    void getMemberByAuth() throws Exception {
        //given
        AuthServiceType oauthType = AuthServiceType.KAKAO;
        String oauthId = "12341234";

        given(getMemberByAuthUseCase.getMemberIdByAuthOrElseZero(oauthType, oauthId))
                .willReturn(1L);

        //when
        ResultActions resultActions = mockMvc.perform(get("/internal/v1/members/oauth/{oauthType}/oauth-id/{oauthId}", oauthType, oauthId))
                .andDo(print())
                .andDo(document("get-member-by-auth/success",
                        pathParameters(
                                RequestDocumentation.parameterWithName("oauthType").description("인증 타입"),
                                RequestDocumentation.parameterWithName("oauthId").description("인증 아이디")
                        ))
                );

        //then
        String response = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(response).isEqualTo("1");
    }

}