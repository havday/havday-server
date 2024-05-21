package com.wane.apigateway.adapter.out.external.member;

import com.wane.apigateway.application.port.out.GetMemberByOauthIdPort;
import com.wane.apigateway.domain.OauthServerType;
import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GetMemberByOauthIdAdapter implements GetMemberByOauthIdPort {

	@Override
	public Long getMemberIdByOauthTypeAndOauthIdOrElseZero(String oauthId, OauthServerType oauthType) {
		String baseUrl = "http://member-service:8080";
		String path = "/api/v1/members/oauth/"+oauthType.getValue()+"/oauth-id/" + oauthId;

		return RestClient.builder()
				.baseUrl(baseUrl + path)
				.defaultStatusHandler(HttpStatusCode::isError, (req, res) -> {
					throw new CustomException(ErrorCode.MEMBER_NOT_MATCH);
				})
				.build()
				.get()
				.retrieve()
				.body(Long.class);
	}
}
