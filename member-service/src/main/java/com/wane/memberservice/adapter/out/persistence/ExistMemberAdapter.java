package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.application.port.out.ExistMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ExistMemberAdapter implements ExistMemberPort {

	private final MemberJpaEntityRepository repository;

	@Override
	public boolean existMemberByEmail(String email) {
		Optional<MemberJpaEntity> optionalMemberJpaEntity = repository.findByEmail(email);

		return optionalMemberJpaEntity.isPresent();
	}
}
