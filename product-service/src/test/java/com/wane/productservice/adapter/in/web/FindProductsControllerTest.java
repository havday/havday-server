package com.wane.productservice.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.productservice.application.port.in.FindProductsUseCase;
import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FindProductsController.class)
@AutoConfigureRestDocs
class FindProductsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private FindProductsUseCase findProductsUseCase;

	@DisplayName("productId 와 size 로 상품들의 리스트를 반환한다.")
	@Test
	void findProducts() throws Exception {
		//given
		String sizeDescription = """
				SIZE - FREE
				VISOR - 7
				DEPTH - 15.5""";

		Product product1 = Product.forMain(1L, "신발", 10000, "https://wan2daaa.com/image.png");
		Product product2 = Product.forMain(2L, "모자", 39000,  "https://wan2daaa.com/image2.png");

		given(findProductsUseCase.findProductsWithCursor(anyLong(), anyInt()))
				.willReturn(new CursorResponse<>(true, List.of(product1, product2)));

		//when & then
		mockMvc.perform(get("/api/v1/products")
						.param("productId", String.valueOf(1))
						.param("size", String.valueOf(2)))
				.andExpect(status().isOk())
				.andDo(print())
				.andDo(document("find-products/success",
						queryParameters(
								parameterWithName("productId").description("상품 id"),
								parameterWithName("size").description("상품 개수")
						),
						responseFields(
								fieldWithPath("hasNext").description("다음 결과가 있는지 true false"),
								fieldWithPath("products[].id").description("상품 id "),
								fieldWithPath("products[].name").description("상품 명"),
								fieldWithPath("products[].price").description("상품 가격"),
								fieldWithPath("products[].imageUrl").description("상품 이미지 주소")

						)));
	}

	@DisplayName("쿼리 파라미터가 없어도는 상품들의 리스트를 반환한다.")
	@Test
	void findProductsWithoutParameters() throws Exception {
		//given
		String sizeDescription = """
				SIZE - FREE
				VISOR - 7
				DEPTH - 15.5""";

		Product product1 = Product.forMain(1L, "신발", 10000, "https://wan2daaa.com/image.png");
		Product product2 = Product.forMain(2L, "모자", 39000,  "https://wan2daaa.com/image2.png");

		given(findProductsUseCase.findProductsWithCursor(anyLong(), anyInt()))
				.willReturn(new CursorResponse<>(true, List.of(product1, product2)));

		//when & then
		mockMvc.perform(get("/api/v1/products"))
				.andExpect(status().isOk())
				.andDo(print())
				.andDo(document("find-products-without-query-parameters/success",
						responseFields(
								fieldWithPath("hasNext").description("다음 결과가 있는지 true false"),
								fieldWithPath("products[].id").description("상품 id "),
								fieldWithPath("products[].name").description("상품 명"),
								fieldWithPath("products[].price").description("상품 가격"),
								fieldWithPath("products[].imageUrl").description("상품 이미지 주소")
						)));
	}


}