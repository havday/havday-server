package com.wane.apigateway.adapter.in.web.dto.request;

public record AuthorizeTokenRequest(
		String redirectUri,
		String code,
		String state
) {
}
