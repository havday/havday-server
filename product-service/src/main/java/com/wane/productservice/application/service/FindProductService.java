package com.wane.productservice.application.service;

import com.wane.productservice.application.port.in.FindProductUseCase;
import com.wane.productservice.application.port.out.FindProductPort;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindProductService implements FindProductUseCase {

	private final FindProductPort findProductPort;

	@Override
	public Product findProduct(Long productId) {
		return findProductPort.findProductById(productId);

	}
}
