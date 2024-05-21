package com.wane.memberservice.application.port.service;

import com.wane.memberservice.application.port.in.GetMemberByAuthUseCase;
import com.wane.memberservice.application.port.out.GetUserByAuthIdAndOauthTypePort;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetMemberByAuthService implements GetMemberByAuthUseCase {

	private final GetUserByAuthIdAndOauthTypePort getUserByAuthIdAndOauthTypePort;

	@Override
	public Long getMemberIdByAuthOrElseZero(AuthServiceType oauthType, String oauthId) {
		Member user = getUserByAuthIdAndOauthTypePort.getUserIdByOauthTypeAndAuthIdOrElseZero(oauthType, oauthId);

		if (user == null) {
			return 0L;
		}

		return user.getId();
	}
}
