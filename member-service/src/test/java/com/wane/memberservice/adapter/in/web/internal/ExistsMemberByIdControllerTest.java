package com.wane.memberservice.adapter.in.web.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.memberservice.application.port.in.ExistsMemberByIdUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(controllers = ExistsMemberByIdController.class)
public class ExistsMemberByIdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExistsMemberByIdUseCase existsMemberByIdUseCase;

    @DisplayName("회원이 존재하는지 id 로 확인한다")
    @Test
    void existsMemberByIdThenReturnTrue() throws Exception {
        //given
        final String memberId = "1";
        given(existsMemberByIdUseCase.existsMemberById(memberId))
                .willReturn(true);

        //when
        ResultActions resultActions = mockMvc.perform(get("/internal/v1/members/exists/{memberId}", memberId))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("exists-member-by-id/success",
                        pathParameters(
                                parameterWithName("memberId").description("회원 id")
                        )
                ));

        //then
        String response = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(response).isEqualTo("true");
    }

}