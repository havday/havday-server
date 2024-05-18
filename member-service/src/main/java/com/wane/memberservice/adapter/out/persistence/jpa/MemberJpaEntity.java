package com.wane.memberservice.adapter.out.persistence.jpa;

import com.wane.memberservice.domain.AuthServiceType;
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

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	private String password;

	private String phoneNumber;

	private int point;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AuthServiceType authServiceType;

	@Column(nullable = false)
	private MemberRole memberRole;

	public MemberJpaEntity(String name, String email, String password, String phoneNumber, int point, AuthServiceType authServiceType, MemberRole memberRole) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.point = point;
		this.authServiceType = authServiceType;
		this.memberRole = memberRole;
	}
}
