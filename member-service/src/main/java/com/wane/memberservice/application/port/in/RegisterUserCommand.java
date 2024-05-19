package com.wane.memberservice.application.port.in;

import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.util.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RegisterUserCommand extends SelfValidating<RegisterUserCommand> {

	@NotEmpty(message = "이름이 비어서는 안됩니다.")
	private final String name;
	@NotEmpty(message = "이메일이 비어서는 안됩니다.")
	private final String email;
	private final String password;
	@NotEmpty(message = "전화번호가 비어서는 안됩니다.")
	@Length(min = 11, max = 11, message = "전화번호는 정확히 11자여야합니다.")
	private final String phoneNumber;
	@NotNull(message = "어느 oauth로 인증한지 명시해야 합니다.")
	private final AuthServiceType authServiceType;

	public RegisterUserCommand(String name, String email, String password, String phoneNumber, AuthServiceType authServiceType) {
		if (authServiceType == AuthServiceType.NONE) {
			throw new IllegalArgumentException("어떤 oauth로 인증한지 명시해야 합니다.");
		}

		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.authServiceType = authServiceType;

		this.validateSelf();
	}


}
