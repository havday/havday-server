package com.wane.orderservice.adapter.out.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.orderservice.application.port.out.FindProductIdAndQuantityListPort;
import com.wane.orderservice.application.port.out.ProductIdAndQuantity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindProductIdAndQuantityListAdapter implements FindProductIdAndQuantityListPort {

    @Value("${external.product-service.url}")
    private String productServiceUrl;

    @Override
    public List<ProductIdAndQuantity> findProductIdAndQuantityListByProductIds(List<Long> productIds) {
        String host = "localhost";
        ObjectMapper objectMapper = new ObjectMapper();

        String stringResponse = RestClient.create().get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host(productServiceUrl)
                        .path("/api/v1/products/id-quantity")
                        .queryParam("productIds", productIds)
                        .build()
                )
                .retrieve()
                .body(String.class);
        log.info("stringResponse: {}", stringResponse);
        try {
            if (stringResponse == null || stringResponse.isEmpty()) return List.of();
            return objectMapper.readValue(stringResponse, new TypeReference<List<ProductIdAndQuantity>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
