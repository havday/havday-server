package com.wane.productservice.adapter.in.web.external;

import com.wane.productservice.adapter.in.web.dto.response.DetailProductResponse;
import com.wane.productservice.application.port.in.FindProductUseCase;
import com.wane.productservice.domain.Product;
import com.wane.productservice.domain.ProductDetailImage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FindProductController {

	private final FindProductUseCase findProductUseCase;

	@GetMapping("/api/v1/no-auth/products/{productId}")
	public ResponseEntity<DetailProductResponse> findProduct(@PathVariable Long productId) {
		Product product = findProductUseCase.findProduct(productId);
		return ResponseEntity.ok(
				new DetailProductResponse(
						product.getId(),
						product.getName(),
						product.getPrice(),
						product.getMaterialDescription(),
						product.getSizeDescription(),
						product.getMainImageUrl(),
						product.getProductDetailImages().stream().map(ProductDetailImage::getImageUrl).toList()
				)
		);
	}

}
