package com.wane.productservice.application.service;

import com.wane.productservice.application.port.out.FindProductsPort;
import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class FindProductsServiceTest {

	@Mock
	private FindProductsPort findProductsPort;

	@InjectMocks
	private FindProductsService findProductsService;

	@DisplayName("상품 리스트를 받아서 CursorResponse 로 바꿔서 반환한다.")
	@Test
	void findProductsWithCursor() {
		//given
		Product product1 = Product.forMain(1L, "신발", 10000, "https://wan2daaa.com/image.png");
		Product product2 = Product.forMain(2L, "모자", 39000, "https://wan2daaa.com/image2.png");

		given(findProductsPort.findProductsOrderByIdAscWithCursor(anyLong(), anyInt()))
				.willReturn(
						List.of(product1, product2)
				);
		//when
		CursorResponse<Product> cursorResponse = findProductsService.findProductsWithCursor(1L, 2);

		//then
		assertThat(cursorResponse.hasNext()).isTrue();
		assertThat(cursorResponse.data()).hasSize(2);
	}

	@DisplayName("상품 리스트의 크기가 size 보다 작으면, hasNext를 false로 반환한다.")
	@Test
	void findProductsWithCursorLessThanSizeProduct() {
		//given
		Product product1 = Product.forMain(1L, "신발", 10000, "https://wan2daaa.com/image.png");

		given(findProductsPort.findProductsOrderByIdAscWithCursor(anyLong(), anyInt()))
				.willReturn(
						List.of(product1)
				);
		//when
		CursorResponse<Product> cursorResponse = findProductsService.findProductsWithCursor(1L, 2);

		//then
		assertThat(cursorResponse.hasNext()).isFalse();
		assertThat(cursorResponse.data()).hasSize(1);
	}


	@DisplayName("상품리스트가 비면 빈 데이터를 반환한다.")
	@Test
	void findProductsWithCursorEmptyProduct() {
		//given
		given(findProductsPort.findProductsOrderByIdAscWithCursor(anyLong(), anyInt()))
				.willReturn(
						List.of()
				);
		//when
		CursorResponse<Product> cursorResponse = findProductsService.findProductsWithCursor(1L, 2);

		//then
		assertThat(cursorResponse.hasNext()).isFalse();
		assertThat(cursorResponse.data()).hasSize(0);
	}

}