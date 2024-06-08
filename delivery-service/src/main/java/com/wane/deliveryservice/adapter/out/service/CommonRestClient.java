package com.wane.deliveryservice.adapter.out.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Component
public class CommonRestClient {

    public RestClient create() {
        return RestClient.create();
    }

    public Object sendGetMethod(URI uri, Class<?> clazz) {
        return RestClient.create().get()
                .uri(uri)
                .retrieve()
                .body(clazz);
    }
}
