package com.wane.apigateway.adapter.out.external.member;

import com.wane.apigateway.application.port.out.GetMemberByOauthIdPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GetMemberByOauthIdAdapter implements GetMemberByOauthIdPort {

	@Override
	public Long getMemberIdByOauthIdOrElseZero(String oauthId) {
		String baseUrl = "http://member-service:8080";
		String path = "/api/v1/members/oauth-id/" + oauthId;

		return RestClient.create(baseUrl + path)
				.get()
				.retrieve()
				.body(Long.class);
	}
}
