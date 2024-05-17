package com.wane.memberservice.common.exception;

public class MemberDuplicateException extends RuntimeException{

	public MemberDuplicateException(String message) {
		super(message);
	}
}
