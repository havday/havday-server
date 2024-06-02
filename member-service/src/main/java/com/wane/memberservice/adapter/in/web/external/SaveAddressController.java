package com.wane.memberservice.adapter.in.web.external;

import com.wane.memberservice.adapter.in.web.dto.request.SaveAddressRequest;
import com.wane.memberservice.application.port.in.SaveAddressCommand;
import com.wane.memberservice.application.port.in.SaveAddressUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SaveAddressController {

    private final SaveAddressUseCase saveAddressUseCase;

    @PostMapping("/api/v1/members/addresses")
    public ResponseEntity<Void> saveAddress(
            @RequestHeader("memberId") Long memberId,
            @RequestBody SaveAddressRequest request
    ) {

        SaveAddressCommand command = new SaveAddressCommand(
                memberId,
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
