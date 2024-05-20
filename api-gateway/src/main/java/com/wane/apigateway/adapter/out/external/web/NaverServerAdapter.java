package com.wane.apigateway.adapter.out.external.web;

import com.wane.apigateway.application.port.out.OauthAccessToken;
import com.wane.apigateway.application.port.out.OauthAuthorizeToken;
import com.wane.apigateway.application.port.out.OauthServerPort;
import com.wane.apigateway.domain.OauthServerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Slf4j
@Component
public class NaverServerAdapter implements OauthServerPort {

	@Override
	public OauthAccessToken authorizeUserAndGetToken(OauthAuthorizeToken command) {
		RestClient restClient = RestClient.builder()
				.baseUrl("https://nid.naver.com/oauth2.0/token")
				.build();

		String clientId = "Je6uPCgSsKTnsiQSE2__";
		String clientSecret = "2LFxkKCsWA";

		Map responseMap = restClient.get()
				.uri(uriBuilder -> uriBuilder
						.queryParam("grant_type", "authorization_code")
						.queryParam("client_id", clientId)
						.queryParam("client_secret", clientSecret)
						.queryParam("code", command.code())
						.queryParam("state", command.state())
						.build()
				)
				.retrieve()
				.body(Map.class);

		if (responseMap == null || responseMap.get("access_token") == null) {
			throw new RuntimeException("네이버 로그인에 실패하였습니다.");
		}

		return new OauthAccessToken(responseMap.get("access_token").toString());
	}

	@Override
	public String getOauthUserId(String oauthAccessToken) {
		RestClient restClient = RestClient.builder()
				.baseUrl("https://openapi.naver.com/v1/nid/me")
				.defaultHeader("Authorization", "Bearer " + oauthAccessToken)
				.build();

		Map responseBodyMap = restClient.get()
				.retrieve()
				.body(Map.class);

		if (responseBodyMap == null) {
			throw new RuntimeException("네이버 로그인에 실패하였습니다.");
		}

		for (Object o : responseBodyMap.keySet()) {
			if (o.equals("response")) {
				Map response = (Map) responseBodyMap.get(o);
				return response.get("id").toString();
			}
		}

		throw new RuntimeException("네이버 로그인에 실패하였습니다.");
	}

	@Override
	public OauthServerType getType() {
		return OauthServerType.NAVER;
	}
}
