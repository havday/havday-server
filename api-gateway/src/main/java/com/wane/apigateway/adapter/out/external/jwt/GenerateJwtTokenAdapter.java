package com.wane.apigateway.adapter.out.external.jwt;

import com.wane.apigateway.application.port.out.GenerateJwtTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.wane.apigateway.adapter.out.external.jwt.JwtTokenDuration.ACCESS_TOKEN_DURATION;
import static com.wane.apigateway.adapter.out.external.jwt.JwtTokenDuration.REFRESH_TOKEN_DURATION;

@RequiredArgsConstructor
@Component
public class GenerateJwtTokenAdapter implements GenerateJwtTokenPort {

	private final JwtTokenUtils jwtTokenUtils;

	@Override
	public String generateAccessToken(String memberId) {
		return jwtTokenUtils.generateJwtToken(Long.valueOf(memberId), ACCESS_TOKEN_DURATION.getExpiredDurationInMillis(), "accessToken");
	}

	@Override
	public String generateRefreshToken(String memberId) {
		return jwtTokenUtils.generateJwtToken(Long.valueOf(memberId), REFRESH_TOKEN_DURATION.getExpiredDurationInMillis(), "refreshToken");
	}
}
