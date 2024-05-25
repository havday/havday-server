package com.wane.productservice.application.port.in;

import java.util.List;

public interface DecreaseEachProductQuantityUseCase {
    void decreaseEachProductQuantity(List<ProductIdAndDecreaseQuantityCommand> commands);
}
