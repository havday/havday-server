package com.wane.memberservice.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.memberservice.adapter.in.web.dto.request.SaveAddressRequest;
import com.wane.memberservice.application.port.in.RegisterUserUseCase;
import com.wane.memberservice.application.port.in.SaveAddressUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SaveAddressController.class)
@AutoConfigureRestDocs
class SaveAddressControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private SaveAddressUseCase saveAddressUseCase;

	@Test
	void saveAddress() throws Exception {
	    //given
		SaveAddressRequest request = new SaveAddressRequest(1L, "재완집", "수취자", "12345", "도로명 주소", "상세 주소", "01012341234", true);

		//when & then
		mockMvc.perform(post("/api/v1/members/addresses")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andDo(document("save-address/success",
						requestFields(
								fieldWithPath("memberId").ignored(),
								fieldWithPath("name").description("배송자명"),
								fieldWithPath("recipient").description("수취인"),
								fieldWithPath("zipCode").description("우편번호"),
								fieldWithPath("roadName").description("도로명 주소"),
								fieldWithPath("detail").description("상세 주소"),
								fieldWithPath("phoneNumber").description("전화번호"),
								fieldWithPath("isBaseAddress").description("기본 배송지 인지 (true, false)"))
				));

	}



}