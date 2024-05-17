package com.wane.memberservice.application.port.in;

import com.wane.memberservice.util.SelfValidated;
import jakarta.validation.constraints.NotEmpty;

public record RegisterMemberCommand(
		@NotEmpty String email
) implements SelfValidated {

	public RegisterMemberCommand {
		this.validateSelf();
	}
}
