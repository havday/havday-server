package com.wane.apigateway.application.port.out;

public record OauthAuthorizeToken(String redirectUri, String code, String state) {
}
