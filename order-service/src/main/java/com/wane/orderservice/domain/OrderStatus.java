package com.wane.orderservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

	BEFORE_DEPOSIT("입금전"),
	PREPARED("배송준비중"),
	DELIVERED("배송중"),
	COMPLETED("배송완료"),
	RETURNING("반품중"),
	RETURN_COMPLETED("반품완료"),
	REFUND_COMPLETED("환불완료")
	;

	private final String name;
}
