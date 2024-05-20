package com.wane.apigateway.application.port.out;

public interface GetMemberByOauthIdPort {

	Long getMemberIdByOauthIdOrElseZero(String oauthId);
}
