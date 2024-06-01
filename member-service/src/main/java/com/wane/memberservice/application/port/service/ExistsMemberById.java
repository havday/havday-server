package com.wane.memberservice.application.port.service;

import com.wane.memberservice.application.port.in.ExistsMemberByIdUseCase;
import com.wane.memberservice.application.port.out.ExistMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExistsMemberById implements ExistsMemberByIdUseCase {

    private final ExistMemberPort existMemberPort;

    @Override
    public boolean existsMemberById(String memberId) {
       return existMemberPort.existMemberByMemberId(memberId);
    }
}
