package com.wane.productservice.application.service;

import com.wane.productservice.application.port.in.FindProductsUseCase;
import com.wane.productservice.application.port.out.FindProductsPort;
import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindProductsService implements FindProductsUseCase {

	private final FindProductsPort findProductsPort;

	@Override
	public CursorResponse<Product> findProductsWithCursor(Long productId, int size) {

		List<Product> products = findProductsPort.findProductsOrderByIdAscWithCursor(productId, size);

		return new CursorResponse<>(products.size() == size, products);
	}
}
