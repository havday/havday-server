package com.wane.apigateway.adapter.out.external.member;

import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ExistsMemberByIdAdapter {

    @Value("${external.member-service.url}")
    private String memberServiceUrl;

    public Boolean existsMemberById(String memberId) {
        return RestClient.builder()
                .baseUrl(memberServiceUrl + "/internal/v1/members/exists/" + memberId)
//                .defaultStatusHandler(HttpStatusCode::is4xxClientError, (req, res) -> {
//                    throw new CustomException(ErrorCode.MEMBER_NOT_MATCH);
//                })
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new CustomException(ErrorCode.MEMBER_NOT_MATCH);
                })
                .build()
                .get()
                .retrieve()
                .body(Boolean.class);
    }

}
