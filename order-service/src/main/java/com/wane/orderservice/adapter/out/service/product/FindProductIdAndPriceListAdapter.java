package com.wane.orderservice.adapter.out.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.orderservice.application.port.out.FindProductIdAndPriceListPort;
import com.wane.orderservice.application.port.out.ProductIdAndPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class FindProductIdAndPriceListAdapter implements FindProductIdAndPriceListPort {

    @Value("${external.product-service.url}")
    private String productServiceUrl;

    @Override
    public List<ProductIdAndPrice> findProductIdAndPriceListByProductIds(List<Long> productIds) {

        ObjectMapper objectMapper = new ObjectMapper();

        String stringResponse = RestClient.create().get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host(productServiceUrl)
                        .path("/internal/v1/products/id-price")
                        .queryParam("productIds", productIds)
                        .build()
                )
                .retrieve()
                .body(String.class);

        try {
            if (stringResponse.isEmpty()) return List.of();
            return objectMapper.readValue(stringResponse, new TypeReference<List<ProductIdAndPrice>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
