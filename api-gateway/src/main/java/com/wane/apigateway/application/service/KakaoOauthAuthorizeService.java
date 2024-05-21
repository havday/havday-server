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
public class KakaoOauthAuthorizeService implements OauthAuthorizePort {
	private final OauthServerTypeFinder oauthServerTypeFinder;

	private final GetMemberByOauthIdPort getMemberByOauthIdPort;
	private final GenerateJwtTokenPort generateJwtTokenPort;

	@Override
	public AuthorizeData oauthAuthorize(OauthAuthorizeCommand command) {

		OauthServerPort oauthServerPort = oauthServerTypeFinder.findWithType(OauthServerType.KAKAO.getValue());

		OauthAuthorizeToken oauthAuthorizeToken = new OauthAuthorizeToken(command.getRedirectUri(), command.getCode(), command.getState());
		OauthAccessToken oauthAccessToken = oauthServerPort.authorizeUserAndGetToken(oauthAuthorizeToken);

		String oauthUserId = oauthServerPort.getOauthUserId(oauthAccessToken.accessToken());

		Long memberId = getMemberByOauthIdPort.getMemberIdByOauthTypeAndOauthIdOrElseZero(oauthUserId, OauthServerType.KAKAO);

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
		return OauthServerType.KAKAO;
	}
}
