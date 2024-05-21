package com.wane.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {



	@ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class})
	public ResponseEntity<String> handleValidationException(IllegalArgumentException e) {
		log.warn("사용자 입력정보 오류 발생 : {}", e.getMessage());
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<String> handleCustomException(CustomException e) {
		log.error(e.getMessage());
		return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(e.getErrorCode().getMessage());
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		log.error(e.getMessage());
		return ResponseEntity.internalServerError().body("서버에 에러가 발생하였습니다. 관리자에게 문의주세요.");
	}


}
