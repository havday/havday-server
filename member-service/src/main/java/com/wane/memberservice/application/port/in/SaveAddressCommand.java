package com.wane.memberservice.application.port.in;

import com.wane.memberservice.util.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SaveAddressCommand extends SelfValidating<SaveAddressCommand> {

	@NotNull(message = "member id는 필수 값 입니다.")
	private final Long memberId;
	@NotEmpty(message = "배송지명는 필수 값 입니다.")
	private final String name;
	@NotEmpty(message = "수취인은 필수 값 입니다.")
	private final String recipient;
	@NotEmpty(message = "우편번호는 필수 값 입니다.")
	private final String zipCode;
	@NotEmpty(message = "도로명 주소는 필수 값 입니다.")
	private final String roadName;
	private final String detail;
	@NotEmpty(message = "수취인 휴대번호는 필수 값 입니다.")
	private final String phoneNumber;
	@NotNull(message = "기본 배송지 유무는 필수 값 입니다.")
	private final Boolean isBaseAddress;

	public SaveAddressCommand(
			Long memberId,
			String name,
			String recipient,
			String zipCode,
			String roadName,
			String detail,
			String phoneNumber,
			Boolean isBaseAddress
	) {
		this.memberId = memberId;
		this.name = name;
		this.recipient = recipient;
		this.zipCode = zipCode;
		this.roadName = roadName;
		this.detail = detail;
		this.phoneNumber = phoneNumber;
		this.isBaseAddress = isBaseAddress;

		this.validateSelf();
	}
}
