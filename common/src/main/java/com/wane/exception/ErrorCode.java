package com.wane.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	MEMBER_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "11", "이미 가입된 회원 입니다."),
	MEMBER_NOT_MATCH(HttpStatus.BAD_REQUEST, "10", "멤버가 존재하지 않습니다."),
	PRODUCT_PRICE_NOT_MATCH(HttpStatus.BAD_REQUEST, "12", "상품과 주문금액이 일치하지 않습니다."),
	DELIVERY_FEE_NOT_MATCH(HttpStatus.BAD_REQUEST, "13", "배송비와 주문금액이 일치하지 않습니다."),
	PRODUCT_QUANTITY_NOT_ENOUGH(HttpStatus.BAD_REQUEST, "14", "상품의 재고가 부족합니다."),
	PRODUCT_QUANTITY_DECREASE_FAILED(HttpStatus.BAD_REQUEST, "15", "상품의 재고를 감소하는데 실패하였습니다."),
	PRODUCT_ID_EMPTY(HttpStatus.BAD_REQUEST, "16", "상품 ID가 비어있습니다."),
	OBJECT_MAPPER_READ_VALUE_FAILED(HttpStatus.BAD_REQUEST, "100", "객체를 읽어오는데 실패하였습니다."),
	UNKNOWN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "-1", "서버에 에러가 발생하였습니다. 관리자에게 문의 하세요.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
