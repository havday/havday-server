package com.wane.memberservice.application.port.out;

import com.wane.memberservice.domain.Member;

public interface SaveMemberPort {


	Member saveMember(Member member);
}
