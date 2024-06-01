package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.application.port.out.ExistMemberPort;
import com.wane.memberservice.domain.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ExistMemberPersistenceAdapter implements ExistMemberPort {

    private final MemberJpaEntityRepository repository;

    @Override
    public boolean existMemberByMemberId(String id) {
        return repository.existsById(Long.valueOf(id));
    }

    @Override
    public boolean existMemberByEmail(String email) {
        Optional<MemberJpaEntity> optionalMemberJpaEntity = repository.findByEmail(email);

        return optionalMemberJpaEntity.isPresent();
    }
}
