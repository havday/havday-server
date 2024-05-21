package com.wane.memberservice.adapter.in.web.dto.request;

import com.wane.memberservice.domain.AuthServiceType;

public record RegisterUserRequest(
		String name,
		String email,
		String password,
		String phoneNumber,
		AuthServiceType authServiceType,
		String authId
) {
}
