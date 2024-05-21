package com.wane.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	MEMBER_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "11", "이미 가입된 회원 입니다."),
	MEMBER_NOT_MATCH(HttpStatus.BAD_REQUEST, "10", "멤버가 존재하지 않습니다."),
	UNKNOWN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "-1", "서버에 에러가 발생하였습니다. 관리자에게 문의 하세요.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
