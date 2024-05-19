package com.wane.productservice.adapter.in.web;

import com.wane.productservice.application.port.in.FindProductsUseCase;
import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FindProductsController {

	private final FindProductsUseCase findProductsUseCase;

	@GetMapping("/api/v1/products")
	public CursorResponse<Product> findProductsWithNoOffset(@RequestParam(defaultValue = "1") Long productId, @RequestParam(defaultValue = "9" ) int size) {

		log.info("productId = {} , size={}", productId, size);
		return findProductsUseCase.findProductsWithCursor(productId, size);
	}
}
