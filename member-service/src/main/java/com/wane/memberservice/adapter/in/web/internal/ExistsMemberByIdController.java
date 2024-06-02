package com.wane.memberservice.adapter.in.web.internal;

import com.wane.memberservice.application.port.in.ExistsMemberByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExistsMemberByIdController {

    private final ExistsMemberByIdUseCase existsMemberByIdUseCase;

    @GetMapping("/internal/v1/members/exists/{memberId}")
    public ResponseEntity<Boolean> existsMemberById(@PathVariable String memberId) {

        boolean existsMember= existsMemberByIdUseCase.existsMemberById(memberId);
        return ResponseEntity.ok(existsMember);
    }
}
