package com.wane.productservice.adapter.in.web.external;

import com.wane.productservice.adapter.in.web.dto.response.FindMainProduct;
import com.wane.productservice.adapter.in.web.dto.response.FindMainProductsResponse;
import com.wane.productservice.application.port.in.FindProductsUseCase;
import com.wane.productservice.common.CursorResponse;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FindProductsController {

    private final FindProductsUseCase findProductsUseCase;

    @GetMapping("/api/v1/no-auth/products")
    public ResponseEntity<FindMainProductsResponse> findProductsWithNoOffset(@RequestParam(defaultValue = "1") Long productId, @RequestParam(defaultValue = "9") int size) {

        CursorResponse<Product> cursorResponse = findProductsUseCase.findProductsWithCursor(productId, size);

        return ResponseEntity.ok(
                new FindMainProductsResponse(
                        cursorResponse.hasNext(),
                        cursorResponse.data().stream().map(FindMainProduct::fromDomainEntity).toList()
                )
        );
    }

}
