package com.wane.memberservice.adapter.out.persistence.jpa;

import com.wane.memberservice.domain.MemberRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MemberJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private MemberRole memberRole;

	public MemberJpaEntity(String email, MemberRole memberRole) {
		this.email = email;
		this.memberRole = memberRole;
	}
}
