package com.wane.apigateway.application.port.in;

import com.wane.util.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class OauthAuthorizeCommand extends SelfValidating<OauthAuthorizeCommand> {

	@NotEmpty(message = "code는 필수 값 입니다.")
	private final String code;

	private final String redirectUri;

	private final String state;

	public OauthAuthorizeCommand(String code, String redirectUri, String state) {
		this.code = code;
		this.redirectUri = redirectUri;
		this.state = state;

		this.validateSelf();
	}
}
