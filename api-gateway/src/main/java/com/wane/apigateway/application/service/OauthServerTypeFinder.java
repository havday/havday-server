package com.wane.apigateway.application.service;

import com.wane.apigateway.application.port.out.OauthServerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OauthServerTypeFinder {

	private final List<OauthServerPort> oauthServerPorts;

	public OauthServerPort findWithType(String type) {
		return oauthServerPorts.stream()
				.filter(oauthServerPort -> oauthServerPort.getType().getValue().equals(type))
				.findAny()
				.orElseThrow(RuntimeException::new);
	}
}
