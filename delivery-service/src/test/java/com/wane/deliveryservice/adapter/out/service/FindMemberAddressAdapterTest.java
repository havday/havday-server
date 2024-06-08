package com.wane.deliveryservice.adapter.out.service;

import com.wane.deliveryservice.application.port.out.MemberAddress;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class FindMemberAddressAdapterTest {

    @InjectMocks
    private FindMemberAddressAdapter findMemberAddressAdapter;

    @Mock
    private CommonRestClient restClient;


    @Test
    public void findMemberAddressReturnsCorrectAddress() throws URISyntaxException {
        // given
        MemberAddress expectedAddress = new MemberAddress("27478", "분포로 113", "101동 101호", "01012341234", "박재완");
        given(restClient.sendGetMethod(any(URI.class), eq(MemberAddress.class))).willReturn(expectedAddress);

        // when
        MemberAddress actualAddress = findMemberAddressAdapter.findMemberAddress(1L, 1L);

        // then
        Assertions.assertThat(actualAddress).isEqualTo(expectedAddress);
    }

    @Test
    void uriTest() throws URISyntaxException {
        // given
        String memberId = "1";
        String addressId = "1";
        URI uri = new URI("http", "localhost:8080", "/internal/v1/members/address", "memberId=" + memberId + "&addressId=" + addressId, null);

        // when & then
        Assertions.assertThat(uri.toString()).isEqualTo("http://localhost:8080/internal/v1/members/address?memberId=1&addressId=1");
    }
}