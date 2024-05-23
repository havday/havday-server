package com.wane.orderservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
	CANCELED("취소"),
	BEFORE_PAYED("결제 전"),
	PAID("결제완료")
	;
	private final String name;
}
