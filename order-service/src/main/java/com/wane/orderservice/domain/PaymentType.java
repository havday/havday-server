package com.wane.orderservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {
	NO_BANKBOOK("무통장 입금", "no-bankbook"),
	CARD("카드 결제", "card")
	;

	private final String name;
	private final String enName;

	public static PaymentType findByEnName(String enName) {
		return switch (enName) {
			case "no-bankbook" -> NO_BANKBOOK;
			case "card" -> CARD;
			default -> throw new IllegalArgumentException("지원하지 않은 결제 타입입니다.");
		};

	}
}
