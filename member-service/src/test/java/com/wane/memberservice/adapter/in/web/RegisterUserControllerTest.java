package com.wane.memberservice.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.memberservice.adapter.in.web.dto.request.RegisterUserRequest;
import com.wane.memberservice.application.port.in.RegisterUserUseCase;
import com.wane.memberservice.domain.AuthServiceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RegisterUserController.class)
@AutoConfigureRestDocs
class RegisterUserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private RegisterUserUseCase registerUserUseCase;

	//TODO. controller 의 displayname은 뭐가 적절할지 고민해보기
	@Test
	void registerUser() throws Exception {

		//given
		RegisterUserRequest request = new RegisterUserRequest("박재완", "wan2daaa@gmail.com", "", "01012341234", AuthServiceType.KAKAO, "authId");

		//when //then
		mockMvc.perform(post("/api/v1/members/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andDo(document("register-user/success",
						requestFields(
								fieldWithPath("name").description("성명"),
								fieldWithPath("email").description("이메일"),
								fieldWithPath("password").description("password (빈값을 넣으시면 됩니다. \"\")").optional(),
								fieldWithPath("phoneNumber").description("전화번호"),
								fieldWithPath("authServiceType").description("oauth 인증 받은 서버 (KAKAO, NAVER) 중 하나입니다."),
								fieldWithPath("authId").description("authId")
						)));
	}


}