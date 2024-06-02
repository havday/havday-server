package com.wane.reviewservice.adapter.`in`.web

import com.wane.reviewservice.IntegrationTestSupport
import com.wane.reviewservice.adapter.out.persistence.jpa.ReviewJpaEntity
import com.wane.reviewservice.adapter.out.persistence.jpa.ReviewJpaEntityRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.restdocs.generate.RestDocumentationGenerator
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.get

class CountReviewByProductControllerTest(
    @Autowired
    private val reviewJpaEntityRepository: ReviewJpaEntityRepository,
) : IntegrationTestSupport() {

    @DisplayName("상품 id로 review의 갯수를 구할 수 있다.")
    @Test
    fun countReviewByProduct() {
        //given


        val productId = 1L

        saveReviewByProductId(productId)
        saveReviewByProductId(productId)
        saveReviewByProductId(productId)

        //when
        //then
        mockMvc.get("/api/v1/no-auth/reviews/product/{productId}/count", productId){
            requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, "/api/v1/reviews/product/{productId}/count")
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect { jsonPath("reviewCount") { value(3) } }
            .andDo {
                handle(
                    document(
                        "count-review-by-product/success",
                        pathParameters(
                            RequestDocumentation.parameterWithName("productId").description("상품 id")
                        ),
                        PayloadDocumentation.responseFields(
                            PayloadDocumentation.fieldWithPath("reviewCount").description("상품에 대한 리뷰의 총 갯수")
                        )
                    )
                )
            }
    }

    private fun saveReviewByProductId(productId: Long) {
        reviewJpaEntityRepository.save(ReviewJpaEntity(1L, "박재완", productId, "상품 굿굿"))
    }
}