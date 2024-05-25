package com.wane.productservice.application.service;

import com.wane.productservice.application.port.in.DecreaseEachProductQuantityUseCase;
import com.wane.productservice.application.port.in.FindProductsUseCase;
import com.wane.productservice.application.port.in.ProductIdAndDecreaseQuantityCommand;
import com.wane.productservice.application.port.out.SaveProductsPort;
import com.wane.productservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class DecreaseEachProductQuantityService implements DecreaseEachProductQuantityUseCase {

    private final FindProductsUseCase findProductsUseCase;
    private final SaveProductsPort saveProductsPort;

    @Override
    public void decreaseEachProductQuantity(List<ProductIdAndDecreaseQuantityCommand> commands) {
        List<Long> productIdsWhichNeedQuantityDecrease = commands.stream().map(ProductIdAndDecreaseQuantityCommand::getProductId).toList();
        List<Product> products = findProductsUseCase.findProductsByProductIdsIn(productIdsWhichNeedQuantityDecrease);

        for (Product product : products) {
            for (ProductIdAndDecreaseQuantityCommand command : commands) {
                if (product.getId().equals(command.getProductId())) {
                    product.decreaseQuantity(command.getQuantity());
                }
            }
        }

        saveProductsPort.saveProducts(products);
    }
}
