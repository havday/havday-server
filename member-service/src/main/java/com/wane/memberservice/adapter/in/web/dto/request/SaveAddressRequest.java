package com.wane.memberservice.adapter.in.web.dto.request;

public record SaveAddressRequest(
		Long memberId,
		String name,
		String recipient,
		String zipCode,
		String roadName,
		String detail,
		String phoneNumber,
		Boolean isBaseAddress
) {
}
