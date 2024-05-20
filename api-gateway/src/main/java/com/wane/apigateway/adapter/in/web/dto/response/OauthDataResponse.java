package com.wane.apigateway.adapter.in.web.dto.response;

public record OauthDataResponse(
		boolean isMemberExist,
		String oauthUserId,
		String oauthType
) {

	public static OauthDataResponse createMemberExists() {
		return new OauthDataResponse(true, "", "");
	}

	public static OauthDataResponse createMemberNotExists(String oauthUserId, String oauthType) {
		return new OauthDataResponse(false, oauthUserId, oauthType);
	}
}
