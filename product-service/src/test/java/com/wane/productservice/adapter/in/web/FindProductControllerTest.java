package com.wane.productservice.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.productservice.application.port.in.FindProductUseCase;
import com.wane.productservice.domain.Product;
import com.wane.productservice.domain.ProductDetailImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FindProductController.class)
@AutoConfigureRestDocs
class FindProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private FindProductUseCase findProductUseCase;

	@DisplayName("productId로 상품를 반환한다.")
	@Test
	void findProduct() throws Exception {
		//given
		long productId = 1L;

		ProductDetailImage image1 = new ProductDetailImage(1L, "https://wan2daaa.com/image1.png");
		ProductDetailImage image2 = new ProductDetailImage(2L, "https://wan2daaa.com/image2.png");
		ProductDetailImage image3 = new ProductDetailImage(3L, "https://wan2daaa.com/image3.png");


		given(findProductUseCase.findProduct(productId))
				.willReturn(Product.forDetail(productId, "신발", 10000, "COTTON 100%",
						"SIZE - FREE\n" +
								"VISOR - 7\n" +
								"DEPTH - 15.5",
						"https://wan2daaa.com/image1.png",
						List.of(image1, image2, image3)
				));

		//when //then
		mockMvc.perform(get("/api/v1/products/{productId}", productId))
				.andExpect(status().isOk())
				.andDo(print())
				.andDo(document("find-product/success",
								pathParameters(
										parameterWithName("productId").description("상품 id")
								),
								responseFields(
										fieldWithPath("id").description("상품 id"),
										fieldWithPath("name").description("상품 명"),
										fieldWithPath("price").description("상품 가격"),
										fieldWithPath("materialDescription").description("상품 설명"),
										fieldWithPath("sizeDescription").description("상품 사이즈 설명"),
										fieldWithPath("mainImageUrl").ignored(),
										fieldWithPath("detailImageUrls[]").description("상품 이미지 주소 리스트"))
						)
				);

	}

}