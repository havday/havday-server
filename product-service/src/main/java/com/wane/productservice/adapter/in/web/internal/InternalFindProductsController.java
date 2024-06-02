package com.wane.productservice.adapter.in.web.internal;

import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import com.wane.productservice.adapter.in.web.dto.response.ProductIdAndPriceResponse;
import com.wane.productservice.adapter.in.web.dto.response.ProductIdAndQuantityResponse;
import com.wane.productservice.application.port.in.FindProductsUseCase;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class InternalFindProductsController {

    private final FindProductsUseCase findProductsUseCase;

    @GetMapping("/internal/v1/products/id-price")
    public ResponseEntity<List<ProductIdAndPriceResponse>> findProductIdAndPriceListByProductIds(@RequestParam("productIds") List<Long> productIds) {

        if (productIds.isEmpty()) {
            throw new CustomException(ErrorCode.PRODUCT_ID_EMPTY);
        }

        List<Product> products = findProductsUseCase.findProductsByProductIdsIn(productIds);

        List<ProductIdAndPriceResponse> responseList = products.stream().map(p -> new ProductIdAndPriceResponse(p.getId(), p.getPrice())).toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/internal/v1/products/id-quantity")
    public ResponseEntity<List<ProductIdAndQuantityResponse>> findProductIdAndQuantityListByProductIds(@RequestParam("productIds") List<Long> productIds) {

        if (productIds.isEmpty()) {
            throw new CustomException(ErrorCode.PRODUCT_ID_EMPTY);
        }

        List<Product> products = findProductsUseCase.findProductsByProductIdsIn(productIds);

        List<ProductIdAndQuantityResponse> responseList = products.stream().map(p -> new ProductIdAndQuantityResponse(p.getId(), p.getQuantity())).toList();
        return ResponseEntity.ok(responseList);
    }

}
