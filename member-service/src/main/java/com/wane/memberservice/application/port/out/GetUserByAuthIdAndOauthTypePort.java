package com.wane.memberservice.application.port.out;

import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.Member;

public interface GetUserByAuthIdAndOauthTypePort {

	Member getUserIdByOauthTypeAndAuthIdOrElseZero(AuthServiceType oauthType, String oauthId);
}
