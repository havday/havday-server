package com.wane.apigateway.application.port.out;

import com.wane.apigateway.domain.OauthServerType;

public interface GetMemberByOauthIdPort {

	Long getMemberIdByOauthTypeAndOauthIdOrElseZero(String oauthId, OauthServerType oauthType);
}
