package com.wane.deliveryservice.adapter.out.service;

import com.wane.deliveryservice.application.port.out.FindMemberAddressPort;
import com.wane.deliveryservice.application.port.out.MemberAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@Component
public class FindMemberAddressAdapter implements FindMemberAddressPort {

    @Value("${external.member-service.url}")
    private String memberServiceUrl;

    private final CommonRestClient restClient;

    @Override
    public MemberAddress findMemberAddress(Long memberId, Long addressId) {
        try {
            URI uri = new URI("http", memberServiceUrl, "/internal/v1/members/address", "memberId=" + memberId + "&addressId=" + addressId, null);
            return (MemberAddress) restClient.sendGetMethod(uri, MemberAddress.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
