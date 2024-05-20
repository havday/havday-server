package com.wane.apigateway.application.port.out;

public interface GenerateJwtTokenPort {

	String generateAccessToken(String memberId);

	String generateRefreshToken(String memberId);
}
