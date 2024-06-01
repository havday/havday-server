package com.wane.apigateway.adapter.out.external.member;

import com.wane.apigateway.application.port.out.GetMemberByOauthIdPort;
import com.wane.apigateway.domain.OauthServerType;
import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GetMemberByOauthIdAdapter implements GetMemberByOauthIdPort {

	@Value("${external.member-service.url}")
	private String memberServiceUrl;

	@Override
	public Long getMemberIdByOauthTypeAndOauthIdOrElseZero(String oauthId, OauthServerType oauthType) {

		String path = "/api/v1/members/oauth/"+oauthType.getValue()+"/oauth-id/" + oauthId;

		return RestClient.builder()
				.baseUrl(memberServiceUrl + path)
				.defaultStatusHandler(HttpStatusCode::isError, (req, res) -> {
					throw new CustomException(ErrorCode.MEMBER_NOT_MATCH);
				})
				.build()
				.get()
				.retrieve()
				.body(Long.class);
	}
}
