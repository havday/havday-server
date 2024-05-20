package com.wane.apigateway.adapter.in.web;

import com.wane.apigateway.adapter.in.web.dto.response.OauthDataResponse;
import com.wane.apigateway.adapter.out.external.jwt.JwtTokenDuration;
import com.wane.apigateway.application.port.in.OauthAuthorizeCommand;
import com.wane.apigateway.application.port.in.OauthAuthorizePort;
import com.wane.apigateway.domain.AuthorizeData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OauthAuthorizeController {

	private final OauthAuthorizeTypeFinder oauthAuthorizeTypeFinder;

	@GetMapping("/api/oauth/{oauthName}/authorize")
	public ResponseEntity<OauthDataResponse> authorize(
			@PathVariable String oauthName,
			@RequestParam(defaultValue = "") String redirectUri,
			@RequestParam(defaultValue = "") String code,
			@RequestParam(defaultValue = "") String state
	) {

		OauthAuthorizePort oauthAuthorizePort = oauthAuthorizeTypeFinder.findWithType(oauthName);
		AuthorizeData authorizeData = oauthAuthorizePort.oauthAuthorize(new OauthAuthorizeCommand(code, redirectUri, state));

		if (authorizeData.isMemberExist()) {
			ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", authorizeData.refreshToken())
					.httpOnly(true)
					.secure(true)
					.path("/")
					.maxAge(JwtTokenDuration.REFRESH_TOKEN_DURATION.getExpiredDurationInSecond())
					.build();
			return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + authorizeData.accessToken())
					.header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
					.body(OauthDataResponse.createMemberExists());
		}

		return ResponseEntity.ok(OauthDataResponse.createMemberNotExists(authorizeData.oauthUserId(), authorizeData.oauthType()));
	}
}
