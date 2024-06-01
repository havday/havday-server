package com.wane.apigateway.filter;

import com.wane.apigateway.adapter.out.external.jwt.JwtTokenUtils;
import com.wane.apigateway.adapter.out.external.member.ExistsMemberByIdAdapter;
import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<Object> {

    private final JwtTokenUtils jwtTokenUtils;
    private final ExistsMemberByIdAdapter existsMemberByIdAdapter;

    private static final String NO_AUTH_URI = "no-auth";

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info("request information : {}", request.getURI());

            if (request.getURI().getPath().contains(NO_AUTH_URI)) {
                return chain.filter(exchange);
            }

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, ErrorCode.AUTHORIZATION_FAIL);
            }

            String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (bearerToken == null || bearerToken.isBlank() || !bearerToken.startsWith("Bearer ")) {
                return onError(exchange, ErrorCode.AUTHORIZATION_FAIL);
            }

            String accessToken = bearerToken.replace("Bearer ", "");

            if (accessToken.isBlank()) {
                return onError(exchange, ErrorCode.AUTHORIZATION_FAIL);
            }

            String memberId;
            try {
                memberId = jwtTokenUtils.getMemberId(accessToken);
            } catch (ExpiredJwtException ignored) {
                //TODO. 만료되면 accessToken 새로 발급 받고 다시 재요청 (client)
                return onError(exchange, ErrorCode.JWT_TOKEN_EXPIRED);
            } catch (Exception e) {
                //TODO : ip 차단 하기
                log.warn("변조된 토큰 : {}", exchange.getRequest().getRemoteAddress());
                return onError(exchange, ErrorCode.AUTHORIZATION_FAIL);
            }

            if (memberId == null) {
                return onError(exchange, ErrorCode.AUTHORIZATION_FAIL);
            }

            Boolean existsMember = existsMemberByIdAdapter.existsMemberById(memberId);

            if (!existsMember) {
                return onError(exchange, ErrorCode.MEMBER_NOT_MATCH);
            }

            exchange.getRequest().mutate().headers(httpHeaders -> httpHeaders.set("memberId", memberId));

            return chain.filter(exchange);
        });

    }

    private Mono<Void> onError(ServerWebExchange exchange, ErrorCode errorCode) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(errorCode.getHttpStatus());
        log.warn("인증 실패 : {}", errorCode.getMessage());

        return Mono.error(new CustomException(errorCode));
    }
}
