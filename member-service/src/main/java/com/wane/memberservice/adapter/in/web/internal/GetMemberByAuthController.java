package com.wane.memberservice.adapter.in.web.internal;

import com.wane.memberservice.application.port.in.GetMemberByAuthUseCase;
import com.wane.memberservice.domain.AuthServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetMemberByAuthController {

	private final GetMemberByAuthUseCase getMemberByAuthUseCase;

	@GetMapping("/internal/v1/members/oauth/{oauthType}/oauth-id/{oauthId}")
	public ResponseEntity<Long> getMemberByAuth(
		@PathVariable AuthServiceType oauthType,
		@PathVariable String oauthId
	) {

		Long memberId = getMemberByAuthUseCase.getMemberIdByAuthOrElseZero(oauthType, oauthId);

		return ResponseEntity.ok(memberId);
	}
}
