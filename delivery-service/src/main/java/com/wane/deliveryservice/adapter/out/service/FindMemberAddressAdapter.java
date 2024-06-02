package com.wane.deliveryservice.adapter.out.service;

import com.wane.deliveryservice.application.port.out.FindMemberAddressPort;
import com.wane.deliveryservice.application.port.out.MemberAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class FindMemberAddressAdapter implements FindMemberAddressPort {

    @Value("${external.member-service.url}")
    private String memberServiceUrl;

    @Override
    public MemberAddress findMemberAddress(Long memberId, Long addressId) {
        return RestClient.create().get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host(memberServiceUrl)
                        .port("8080")
                        .path("/internal/v1/members/address/")
                        .queryParam("memberId", memberId)
                        .queryParam("addressId", addressId)
                        .build()
                )
                .retrieve()
                .body(MemberAddress.class);
    }
}
