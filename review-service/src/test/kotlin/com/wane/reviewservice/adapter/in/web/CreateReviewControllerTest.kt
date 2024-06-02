package com.wane.reviewservice.adapter.`in`.web

import com.wane.reviewservice.IntegrationTestSupport
import com.wane.reviewservice.adapter.`in`.web.dto.request.CreateReviewRequest
import com.wane.reviewservice.application.port.`in`.CreateReviewCommand
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.test.web.servlet.post


class CreateReviewControllerTest : IntegrationTestSupport() {

    @DisplayName("리뷰를 생성할 수 있다.")
    @Test
    fun createReview() {

        //given
        val request = CreateReviewRequest( "박재완", 1L, "모자가 참 이쁘네요")

        //when
        //then
        mockMvc.post("/api/v1/reviews") {
            header("memberId", 1L)
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }
            .andDo { print() }
            .andExpect { status { isCreated() } }
            .andDo {
                handle(document(
                    "create-review/success",
                    requestHeaders(
                        headerWithName("memberId").description("멤버 id")
                    ),
                    requestFields(
                        fieldWithPath("memberName").description("멤버 실명"),
                        fieldWithPath("productId").description("상품 id"),
                        fieldWithPath("content").description("리뷰 내용")
                    )
                ))
            }
    }
}