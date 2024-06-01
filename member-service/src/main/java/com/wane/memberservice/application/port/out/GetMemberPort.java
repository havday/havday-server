package com.wane.memberservice.application.port.out;

import com.wane.memberservice.domain.Member;

public interface GetMemberPort {

	Member getUser(Long memberId);
}
