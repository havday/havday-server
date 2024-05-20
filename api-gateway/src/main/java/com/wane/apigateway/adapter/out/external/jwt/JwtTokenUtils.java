package com.wane.apigateway.adapter.out.external.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtTokenUtils {

	private final SecretKey secretKey;

	public JwtTokenUtils(@Value("${jwt.secret-key}") String key) {
		this.secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
	}

	public Boolean isTokenValid(String token, Long memberId) {
		Long id = Long.valueOf(getMemberId(token));
		return id.equals(memberId);
	}


	public Jws<Claims> parseJwtToken(String token) {
		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token);
	}

	public String getMemberId(String token) throws ExpiredJwtException{
		return parseJwtToken(token).getPayload().get("memberId").toString();
	}


	public String generateJwtToken(Long memberId, long expiredTimeMillis, String tokenName) {
		return Jwts.builder()
				.id(UUID.randomUUID().toString())
				.subject(tokenName)
				.claim("memberId", memberId)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiredTimeMillis))
				.signWith(secretKey)
				.compact();
	}

}
