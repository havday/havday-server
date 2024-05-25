package com.wane.productservice.adapter.in.web;

import com.wane.productservice.adapter.in.web.dto.request.ProductIdAndDecreaseQuantity;
import com.wane.productservice.application.port.in.DecreaseEachProductQuantityUseCase;
import com.wane.productservice.application.port.in.ProductIdAndDecreaseQuantityCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DecreaseProductQuantityController {

    private final DecreaseEachProductQuantityUseCase decreaseProductQuantityUseCase;

    @PutMapping("/products/decrease-quantity")
    public ResponseEntity<Void> decreaseProductQuantity(
            @RequestBody List<ProductIdAndDecreaseQuantity> requestList
    ) {
        List<ProductIdAndDecreaseQuantityCommand> commands = requestList.stream()
                .map(req -> new ProductIdAndDecreaseQuantityCommand(req.productId(), req.quantity()))
                .toList();
        decreaseProductQuantityUseCase.decreaseEachProductQuantity(commands);

        return ResponseEntity.noContent().build();

    }
}
