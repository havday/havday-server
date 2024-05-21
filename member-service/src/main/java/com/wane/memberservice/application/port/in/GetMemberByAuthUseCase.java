package com.wane.memberservice.application.port.in;

import com.wane.memberservice.domain.AuthServiceType;

public interface GetMemberByAuthUseCase {
	Long getMemberIdByAuthOrElseZero(AuthServiceType oauthType, String oauthId);
}
