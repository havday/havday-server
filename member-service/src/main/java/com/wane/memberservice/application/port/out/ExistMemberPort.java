package com.wane.memberservice.application.port.out;

public interface ExistMemberPort {

	boolean existMemberByMemberId(String id);
	boolean existMemberByEmail(String email);
}
