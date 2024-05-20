package com.wane.apigateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OauthServerType {
	NAVER("naver"),
	KAKAO("kakao");
	private final String value;
}
