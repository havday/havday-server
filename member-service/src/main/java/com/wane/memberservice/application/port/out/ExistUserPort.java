package com.wane.memberservice.application.port.out;

public interface ExistUserPort {

	boolean existUserByEmail(String email);
}
