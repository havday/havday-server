package com.wane.apigateway.fake;

import com.wane.apigateway.application.port.in.OauthAuthorizeCommand;
import com.wane.apigateway.application.port.in.OauthAuthorizePort;
import com.wane.apigateway.domain.AuthorizeData;
import com.wane.apigateway.domain.OauthServerType;

public class FakeMemberExistsOauthAuthorizePort implements OauthAuthorizePort {
	@Override
	public AuthorizeData oauthAuthorize(OauthAuthorizeCommand command) {
		return AuthorizeData.createMemberExists("accessToken", "refreshToken");
	}

	@Override
	public OauthServerType getType() {
		return null;
	}
}
