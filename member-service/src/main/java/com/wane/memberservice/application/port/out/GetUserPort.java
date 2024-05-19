package com.wane.memberservice.application.port.out;

import com.wane.memberservice.domain.Member;

public interface GetUserPort {

	Member getUser(Long memberId);
}
