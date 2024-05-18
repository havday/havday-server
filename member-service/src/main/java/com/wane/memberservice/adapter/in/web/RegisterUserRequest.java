package com.wane.memberservice.adapter.in.web;

import com.wane.memberservice.domain.AuthServiceType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequest(
		String name,
		String email,
		String password,
		String phoneNumber,
		AuthServiceType authServiceType
) {
}
