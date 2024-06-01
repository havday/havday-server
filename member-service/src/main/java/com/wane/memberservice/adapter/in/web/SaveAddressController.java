package com.wane.memberservice.adapter.in.web;

import com.wane.memberservice.adapter.in.web.dto.request.SaveAddressRequest;
import com.wane.memberservice.application.port.in.SaveAddressCommand;
import com.wane.memberservice.application.port.in.SaveAddressUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SaveAddressController {

	private final SaveAddressUseCase saveAddressUseCase;

	@PostMapping("/api/v1/members/addresses")
	public ResponseEntity<Void> saveAddress(@RequestBody SaveAddressRequest request) {

		//TODO : memberId 를 api gateway에서 받을거고 임시로 requestBody에 받게 수정

		SaveAddressCommand command = new SaveAddressCommand(
				request.memberId(),
				request.name(),
				request.recipient(),
				request.zipCode(),
				request.roadName(),
				request.detail(),
				request.phoneNumber(),
				request.isBaseAddress()
		);

		saveAddressUseCase.saveAddress(command);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
