package com.wane.apigateway.adapter.in.web;

import com.wane.apigateway.adapter.out.external.jwt.JwtTokenDuration;
import com.wane.apigateway.adapter.out.external.jwt.JwtTokenUtils;
import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReissueTokenController {

    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("/api/v1/jwt/reissue")
    public Mono<ResponseEntity<Void>> reissueToken(ServerWebExchange exchange) {
        String refreshToken = findRefreshTokenWithExchange(exchange);

        String accessToken = reissueAccessTokenWithRefreshToken(refreshToken);

        return Mono.just(ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .build());
    }

    private String findRefreshTokenWithExchange(ServerWebExchange exchange) {
        List<HttpCookie> cookies = exchange.getRequest().getCookies().get("refreshToken");

        if (cookies == null || cookies.isEmpty()) {
            throw new CustomException(ErrorCode.AUTHORIZATION_FAIL);
        }

        return findRefreshTokenWithCookies(cookies);
    }

    private String findRefreshTokenWithCookies(List<HttpCookie> cookieList) {
        return cookieList.stream()
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.AUTHORIZATION_FAIL))
                .getValue();
    }

    private String reissueAccessTokenWithRefreshToken(String refreshToken) {
        String memberId = jwtTokenUtils.getMemberId(refreshToken);
        String accessToken = jwtTokenUtils.generateJwtToken(Long.valueOf(memberId), JwtTokenDuration.ACCESS_TOKEN_DURATION.getExpiredDurationInMillis(), "accessToken");
        return accessToken;
    }
}
