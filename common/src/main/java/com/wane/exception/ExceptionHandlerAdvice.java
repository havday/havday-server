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
    public ResponseEntity<ErrorResponse> handleValidationException(Exception e) {
        log.error("사용자 입력정보 오류 발생 :", e);
        return ResponseEntity.badRequest().body(ErrorResponse.of(ErrorCode.VALIDATION_FAIL));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("CustomException 발생 : {}  ", e.getErrorCode().getCode(), e);
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(ErrorResponse.of(e.getErrorCode()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("서버 에러 발생 ", e);
        return ResponseEntity.internalServerError().body(ErrorResponse.of(ErrorCode.UNKNOWN_SERVER_ERROR));
    }


}
