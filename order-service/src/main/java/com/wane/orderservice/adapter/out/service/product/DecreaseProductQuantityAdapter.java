package com.wane.orderservice.adapter.out.service.product;

import com.wane.exception.CustomException;
import com.wane.orderservice.application.port.out.DecreaseProductQuantityPort;
import com.wane.orderservice.domain.ProductItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

import static com.wane.exception.ErrorCode.PRODUCT_QUANTITY_DECREASE_FAILED;

@Slf4j
@Component
public class DecreaseProductQuantityAdapter implements DecreaseProductQuantityPort {

    @Value("${external.product-service.url}")
    private String productServiceUrl;

    @Override
    public void decreaseProductQuantity(List<ProductItem> productItems) {


        List<ProductIdWithDecreaseQuantity> requestBody = productItems.stream().
                map(productItem ->
                        new ProductIdWithDecreaseQuantity(productItem.getProductId(), productItem.getQuantity())
                ).toList();

        RestClient.create().put()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host(productServiceUrl)
                        .path("/internal/v1/products/decrease-quantity")
                        .build()
                )
                .body(requestBody)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.isError() || httpStatusCode.is4xxClientError(), (req, res) -> {
                    throw new CustomException(PRODUCT_QUANTITY_DECREASE_FAILED);
                });

    }
}
