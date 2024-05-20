package com.wane.apigateway.adapter.in.web;

import com.wane.apigateway.application.port.in.OauthAuthorizePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OauthAuthorizeTypeFinder {

	private final List<OauthAuthorizePort> oauthAuthorizePorts;

	public OauthAuthorizePort findWithType(String type) {
		return oauthAuthorizePorts.stream()
				.filter(oauthAuthorizePort -> oauthAuthorizePort.getType().getValue().equals(type))
				.findAny()
				.orElseThrow(RuntimeException::new);
	}

}
