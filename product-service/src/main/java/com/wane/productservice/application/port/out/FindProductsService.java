package com.wane.productservice.application.port.out;

import com.wane.productservice.application.port.in.FindProductsUseCase;
import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindProductsService implements FindProductsUseCase {

	private final FindProductsPort findProductsPort;

	@Override
	public CursorResponse<Product> findProductsWithCursor(Long productId, int size) {

		return findProductsPort.findProductsWithCursor(productId, size);
	}
}
