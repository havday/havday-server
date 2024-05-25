package com.wane.productservice.application.port.in;

import com.wane.util.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductIdAndDecreaseQuantityCommand extends SelfValidating<ProductIdAndDecreaseQuantityCommand> {
    @NotNull(message = "product id 는 null 일 수 없습니다.")
    private final Long productId;
    @NotNull(message = "quantity 는 null 일 수 없습니다.")
    private final Integer quantity;

    public ProductIdAndDecreaseQuantityCommand(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.validateSelf();
    }
}
