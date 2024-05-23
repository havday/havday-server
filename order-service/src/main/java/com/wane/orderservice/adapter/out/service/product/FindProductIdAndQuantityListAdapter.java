package com.wane.orderservice.adapter.out.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.orderservice.application.port.out.FindProductIdAndQuantityListPort;
import com.wane.orderservice.application.port.out.ProductIdAndQuantity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FindProductIdAndQuantityListAdapter implements FindProductIdAndQuantityListPort {
    @Override
    public List<ProductIdAndQuantity> findProductIdAndQuantityListByProductIds(List<Long> productIds) {
        ObjectMapper objectMapper = new ObjectMapper();

        String stringResponse = RestClient.create().get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("product-service")
                        .port("8080")
                        .path("products/id-quantity")
                        .queryParam("productIds", productIds)
                        .build()
                )
                .retrieve()
                .body(String.class);

        try {
            if (stringResponse == null || stringResponse.isEmpty()) return List.of();
            return objectMapper.readValue(stringResponse, new TypeReference<List<ProductIdAndQuantity>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
