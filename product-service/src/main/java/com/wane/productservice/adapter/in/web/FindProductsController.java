package com.wane.productservice.adapter.in.web;

import com.wane.productservice.adapter.in.web.dto.response.FindProduct;
import com.wane.productservice.adapter.in.web.dto.response.FindProductsResponse;
import com.wane.productservice.application.port.in.FindProductsUseCase;
import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FindProductsController {

	private final FindProductsUseCase findProductsUseCase;

	@GetMapping("/api/v1/products")
	public ResponseEntity<FindProductsResponse> findProductsWithNoOffset(@RequestParam(defaultValue = "1") Long productId, @RequestParam(defaultValue = "9" ) int size) {

		CursorResponse<Product> cursorResponse = findProductsUseCase.findProductsWithCursor(productId, size);

		return ResponseEntity.ok(
				new FindProductsResponse(
						cursorResponse.hasNext(),
						cursorResponse.data().stream().map(FindProduct::fromDomainEntity).toList()
				)
		);
	}

}
