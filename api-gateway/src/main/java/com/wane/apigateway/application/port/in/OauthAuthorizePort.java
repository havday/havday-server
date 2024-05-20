package com.wane.apigateway.application.port.in;

import com.wane.apigateway.domain.AuthorizeData;
import com.wane.apigateway.domain.OauthServerType;

public interface OauthAuthorizePort {

	AuthorizeData oauthAuthorize(OauthAuthorizeCommand command);

	OauthServerType getType();
}
