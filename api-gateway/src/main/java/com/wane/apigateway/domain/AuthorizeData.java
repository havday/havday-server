package com.wane.apigateway.domain;

public record AuthorizeData(
		boolean isMemberExist,
		String accessToken,
		String refreshToken,
		String oauthUserId,
		String oauthType

) {

	public static AuthorizeData createMemberExists(String accessToken, String refreshToken) {
		return new AuthorizeData(true, accessToken, refreshToken, "", "");
	}

	public static AuthorizeData createMemberNotExists(String oauthUserId, String oauthType) {
		return new AuthorizeData(false, "", "", oauthUserId, oauthType);
	}
}
