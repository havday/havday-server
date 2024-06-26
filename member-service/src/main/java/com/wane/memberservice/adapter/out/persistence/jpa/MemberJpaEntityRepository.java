package com.wane.memberservice.adapter.out.persistence.jpa;

import com.wane.memberservice.domain.AuthServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaEntityRepository extends JpaRepository<MemberJpaEntity, Long> {
    Optional<MemberJpaEntity> findByEmail(String email);

    Optional<MemberJpaEntity> findByAuthServiceTypeAndAuthId(AuthServiceType authServiceType, String authId);
}