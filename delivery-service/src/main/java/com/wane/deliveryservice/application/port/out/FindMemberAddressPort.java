package com.wane.deliveryservice.application.port.out;

public interface FindMemberAddressPort {
    MemberAddress findMemberAddress(Long memberId, Long addressId);
}
