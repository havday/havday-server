package com.wane.memberservice.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaEntityRepository extends JpaRepository<MemberJpaEntity, Long> {
	Optional<MemberJpaEntity> findByEmail(String email);
}