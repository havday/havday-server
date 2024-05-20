package com.wane.apigateway.application.port.out;

import com.wane.apigateway.domain.OauthServerType;

public interface OauthServerPort {


	OauthAccessToken authorizeUserAndGetToken(OauthAuthorizeToken command);
	String getOauthUserId(String oauthAccessToken);
	OauthServerType getType();

}
