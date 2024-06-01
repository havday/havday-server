package com.wane.apigateway.adapter.in.web;

import com.wane.apigateway.adapter.in.web.dto.response.OauthDataResponse;
import com.wane.apigateway.adapter.out.external.jwt.JwtTokenDuration;
import com.wane.apigateway.adapter.out.external.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wane.apigateway.adapter.out.external.jwt.JwtTokenDuration.ACCESS_TOKEN_DURATION;
import static com.wane.apigateway.adapter.out.external.jwt.JwtTokenDuration.REFRESH_TOKEN_DURATION;

@RequiredArgsConstructor
@RestController
public class TestJwtController {

    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("/api/v1/jwt/test")
    public ResponseEntity<Void> generateJwt() {
        String accessToken = jwtTokenUtils.generateJwtToken(1L, ACCESS_TOKEN_DURATION.getExpiredDurationInMillis(), "accessToken");
        String refreshToken = jwtTokenUtils.generateJwtToken(1L, REFRESH_TOKEN_DURATION.getExpiredDurationInMillis(), "refreshToken");

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(JwtTokenDuration.REFRESH_TOKEN_DURATION.getExpiredDurationInSecond())
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }
}
