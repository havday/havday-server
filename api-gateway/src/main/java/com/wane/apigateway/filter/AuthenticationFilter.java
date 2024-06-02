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

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<Object> {

    private final JwtTokenUtils jwtTokenUtils;
    private final ExistsMemberByIdAdapter existsMemberByIdAdapter;

    private static final String API_TYPE_HEADER_NAME = "Api-Type";
    private static final String NO_AUTH_HEADER_VALUE = "no-auth";
    private static final String API_VERSION_PATH = "/api/v1";
    private static final String NO_AUTH_CONTAIN_PATH = "/api/v1/no-auth";

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info("request information : {}", request.getURI());

            if (isApiTypeNoAuth(request)) {
                String addNoAuthToPath = request.getPath().value().replace(API_VERSION_PATH, NO_AUTH_CONTAIN_PATH);

                ServerWebExchange modifiedExchange = changePathToExchange(exchange, addNoAuthToPath);

                return chain.filter(modifiedExchange);
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

            ServerWebExchange modifiedExchange = addHeaderToExchange(exchange, memberId);

            return chain.filter(modifiedExchange);
        });

    }

    private static ServerWebExchange changePathToExchange(ServerWebExchange exchange, String addNoAuthToPath) {
        return exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .path(addNoAuthToPath)
                        .build())
                .build();
    }

    private static ServerWebExchange addHeaderToExchange(ServerWebExchange exchange, String memberId) {
        return exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .header("memberId", memberId)
                        .build())
                .build();
    }

    private static boolean isApiTypeNoAuth(ServerHttpRequest request) {
        return Objects.equals(request.getHeaders().getFirst(API_TYPE_HEADER_NAME), NO_AUTH_HEADER_VALUE);
    }

    private Mono<Void> onError(ServerWebExchange exchange, ErrorCode errorCode) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(errorCode.getHttpStatus());
        log.warn("인증 실패 : {}", errorCode.getMessage());

        return Mono.error(new CustomException(errorCode));
    }
}
