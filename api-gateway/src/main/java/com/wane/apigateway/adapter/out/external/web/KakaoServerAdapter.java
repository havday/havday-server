package com.wane.apigateway.adapter.out.external.web;

import com.wane.apigateway.application.port.out.OauthAccessToken;
import com.wane.apigateway.application.port.out.OauthAuthorizeToken;
import com.wane.apigateway.application.port.out.OauthServerPort;
import com.wane.apigateway.domain.OauthServerType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class KakaoServerAdapter implements OauthServerPort {

	@Override
	public OauthAccessToken authorizeUserAndGetToken(OauthAuthorizeToken command) {
		RestClient restClient = RestClient.builder()
				.baseUrl("https://kauth.kakao.com/oauth/token")
				.defaultHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
				.build();

		String clientId = "bcbf2865da16f3883d7b3246f7572b75";
		String clientSecret = "XFZ60Apj6XaC2EjvgGFhHKONE0iZWAZY";


		Map responseMap = restClient.get()
				.uri(uriBuilder -> uriBuilder
						.queryParam("grant_type", "authorization_code")
						.queryParam("client_id", clientId)
						.queryParam("client_secret", clientSecret)
						.queryParam("code", command.code())
						.build()
				)
				.retrieve()
				.body(Map.class);

		if (responseMap.get("access_token") == null) {
			throw new RuntimeException("카카오 로그인에 실패하였습니다.");
		}

		return new OauthAccessToken(responseMap.get("access_token").toString());
	}

	@Override
	public String getOauthUserId(String oauthAccessToken) {
		RestClient restClient = RestClient.builder()
				.baseUrl("https://kapi.kakao.com/v2/user/me?secure_resource=true")
				.defaultHeader("Authorization", "Bearer " + oauthAccessToken)
				.defaultHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
				.build();

		Map responseBodyMap = restClient.get()
				.retrieve()
				.body(Map.class);

		if (responseBodyMap == null || responseBodyMap.get("id") == null) {
			throw new RuntimeException("카카오 로그인에 실패하였습니다.");
		}

		return responseBodyMap.get("id").toString();

	}

	@Override
	public OauthServerType getType() {
		return OauthServerType.KAKAO;
	}
}
