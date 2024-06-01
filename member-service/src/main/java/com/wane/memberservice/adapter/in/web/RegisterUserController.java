package com.wane.memberservice.adapter.in.web;

import com.wane.memberservice.adapter.in.web.dto.request.RegisterUserRequest;
import com.wane.memberservice.application.port.in.RegisterUserCommand;
import com.wane.memberservice.application.port.in.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegisterUserController {

	private final RegisterUserUseCase registerUserUseCase;

	@PostMapping("/api/v1/members/users")
	public ResponseEntity<Void> registerUser(
			@RequestBody RegisterUserRequest request
	) {

		RegisterUserCommand command = new RegisterUserCommand(
				request.name(),
				request.email(),
				request.password(),
				request.phoneNumber(),
				request.authServiceType(),
				request.authId()
		);

		registerUserUseCase.registerUser(command);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
