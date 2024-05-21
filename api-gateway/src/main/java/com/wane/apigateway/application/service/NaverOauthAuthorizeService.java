package com.wane.apigateway.application.service;

import com.wane.apigateway.application.port.in.OauthAuthorizeCommand;
import com.wane.apigateway.application.port.in.OauthAuthorizePort;
import com.wane.apigateway.application.port.out.*;
import com.wane.apigateway.domain.AuthorizeData;
import com.wane.apigateway.domain.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NaverOauthAuthorizeService implements OauthAuthorizePort {

	private final OauthServerTypeFinder oauthServerTypeFinder;
	private final GetMemberByOauthIdPort getMemberByOauthIdPort;
	private final GenerateJwtTokenPort generateJwtTokenPort;

	@Override
	public AuthorizeData oauthAuthorize(OauthAuthorizeCommand command) {

		OauthServerPort serverPort = oauthServerTypeFinder.findWithType(OauthServerType.NAVER.getValue());

		OauthAuthorizeToken oauthAuthorizeToken = new OauthAuthorizeToken(command.getRedirectUri(), command.getCode(), command.getState());
		OauthAccessToken oauthAccessToken = serverPort.authorizeUserAndGetToken(oauthAuthorizeToken);

		String oauthUserId = serverPort.getOauthUserId(oauthAccessToken.accessToken());

		Long memberId = getMemberByOauthIdPort.getMemberIdByOauthTypeAndOauthIdOrElseZero(oauthUserId, OauthServerType.NAVER);


		if (memberId == 0) {
			return AuthorizeData.createMemberExists(
					generateJwtTokenPort.generateAccessToken(oauthUserId),
					generateJwtTokenPort.generateRefreshToken(oauthUserId)
			);
		}

		return AuthorizeData.createMemberNotExists(oauthUserId, OauthServerType.NAVER.getValue());

	}

	@Override
	public OauthServerType getType() {
		return OauthServerType.NAVER;
	}
}
