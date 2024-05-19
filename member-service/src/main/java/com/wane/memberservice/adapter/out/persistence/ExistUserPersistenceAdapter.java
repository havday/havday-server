package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.application.port.out.ExistUserPort;
import com.wane.memberservice.domain.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ExistUserPersistenceAdapter implements ExistUserPort {

	private final MemberJpaEntityRepository repository;

	@Override
	public boolean existUserByEmail(String email) {
		Optional<MemberJpaEntity> optionalMemberJpaEntity = repository.findByEmailAndMemberRole(email, MemberRole.USER);

		return optionalMemberJpaEntity.isPresent();
	}
}
