package com.wane.memberservice.application.port.service;

import com.wane.memberservice.application.port.in.SaveAddressCommand;
import com.wane.memberservice.application.port.in.SaveAddressUseCase;
import com.wane.memberservice.application.port.out.GetMemberPort;
import com.wane.memberservice.application.port.out.SaveMemberPort;
import com.wane.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SaveAddressService implements SaveAddressUseCase {

	private final GetMemberPort getMemberPort;

	private final SaveMemberPort saveMemberPort;


	@Override
	public void saveAddress(SaveAddressCommand command) {
		Member user = getMemberPort.getUser(command.getMemberId());
		user.addAddress(command.getName(), command.getZipCode(), command.getRoadName(), command.getDetail(), command.getPhoneNumber(), command.getRecipient(), command.getIsBaseAddress());
		saveMemberPort.saveMember(user);
	}
}
