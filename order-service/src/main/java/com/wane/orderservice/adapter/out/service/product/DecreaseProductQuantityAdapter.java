package com.wane.orderservice.adapter.out.service.product;

import com.wane.exception.CustomException;
import com.wane.orderservice.application.port.out.DecreaseProductQuantityPort;
import com.wane.orderservice.domain.ProductItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

import static com.wane.exception.ErrorCode.PRODUCT_QUANTITY_DECREASE_FAILED;

@Slf4j
@Component
public class DecreaseProductQuantityAdapter implements DecreaseProductQuantityPort {
    @Override
    public void decreaseProductQuantity(List<ProductItem> productItems) {

        RestClient.create().put()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("product-service")
                        .port("8080")
                        .path("products/decrease-quantity")
                        .build()
                )
                .body(productItems)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.isError() || httpStatusCode.is4xxClientError(), (req, res) -> {
                    throw new CustomException(PRODUCT_QUANTITY_DECREASE_FAILED);
                });

    }
}
