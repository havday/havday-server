package com.wane.apigateway.adapter.in.web;

import com.wane.apigateway.fake.FakeMemberExistsOauthAuthorizePort;
import com.wane.apigateway.fake.FakeMemberNotExistsOauthAuthorizePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@WebFluxTest(OauthAuthorizeController.class)
@ExtendWith(RestDocumentationExtension.class)
class OauthAuthorizeControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private OauthAuthorizeTypeFinder oauthAuthorizeTypeFinder;

	@BeforeEach
	void setUp(ApplicationContext applicationContext, RestDocumentationContextProvider restDocumentation) {
		this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
				.configureClient()
				.filter(documentationConfiguration(restDocumentation))
				.build();
	}

	@DisplayName("네이버를 사용해서 시작하고, 멤버가 존재하는 경우")
	@Test
	void authorizeNaverAndMemberExists() throws Exception {
		//given
		given(oauthAuthorizeTypeFinder.findWithType(any()))
				.willReturn(new FakeMemberExistsOauthAuthorizePort());

		//when & then
		webTestClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/oauth/{oauthName}/authorize")
						.queryParam("redirectUri", "")
						.queryParam("code", "code")
						.queryParam("state", "state")
						.build("naver"))
				.exchange()
				.expectStatus().isOk()
				.expectHeader().exists(HttpHeaders.AUTHORIZATION)
				.expectHeader().exists(HttpHeaders.SET_COOKIE)
				.expectBody()
				.consumeWith(System.out::println)
				.consumeWith(document("auth/naver/member-exists/success",
								queryParameters(
										parameterWithName("redirectUri").description("naver 일때는 빈값"),
										parameterWithName("code").description("redirect 후 받은 코드 값"),
										parameterWithName("state").description("처음 인증할 때 넣어준 state 값")
								),
								responseHeaders(
										headerWithName(HttpHeaders.AUTHORIZATION).description("accessToken"),
										headerWithName(HttpHeaders.SET_COOKIE).description("refreshToken")
								),
								responseFields(
										fieldWithPath("isMemberExist").description("멤버가 존재해서 true"),
										fieldWithPath("oauthUserId").description("빈값"),
										fieldWithPath("oauthType").description("빈값")
								)
						)
				);


	}

	@DisplayName("네이버를 사용해서 시작하고, 멤버가 존재하지 않을 때")
	@Test
	void authorizeNaverAndMemberNotExists() throws Exception {
		//given
		given(oauthAuthorizeTypeFinder.findWithType(any()))
				.willReturn(new FakeMemberNotExistsOauthAuthorizePort());

		//when & then
		webTestClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/oauth/{oauthName}/authorize")
						.queryParam("redirectUri", "")
						.queryParam("code", "code")
						.queryParam("state", "state")
						.build("naver"))
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.consumeWith(System.out::println)
				.consumeWith(document("auth/naver/member-not-exists/success",
								queryParameters(
										parameterWithName("redirectUri").description("naver 일때는 빈값"),
										parameterWithName("code").description("redirect 후 받은 코드 값"),
										parameterWithName("state").description("처음 인증할 때 넣어준 state 값")
								),
								responseFields(
										fieldWithPath("isMemberExist").description("신규 회원이라서 false"),
										fieldWithPath("oauthUserId").description("oauthUserId 추가 정보 받고 같이 넘겨줘야 합니다."),
										fieldWithPath("oauthType").description("oauthType 추가 정보 받고 같이 넘겨줘야 합니다.")
								)
						)
				);
	}

	@DisplayName("카카오를 사용해서 시작하고, 멤버가 존재할 때")
	@Test
	void authorizeKakaoAndMemberExists() throws Exception {
		//given
		given(oauthAuthorizeTypeFinder.findWithType(any()))
				.willReturn(new FakeMemberExistsOauthAuthorizePort());

		//when & then
		webTestClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/oauth/{oauthName}/authorize")
						.queryParam("redirectUri", "https://localhost:3000/naver/fallback")
						.queryParam("code", "code")
						.queryParam("state", "")
						.build("kakao"))
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.consumeWith(System.out::println)
				.consumeWith(document("auth/kakao/member-exists/success",
								queryParameters(
										parameterWithName("redirectUri").description("시작하기 눌렀을 때 넣어준 redirectUri"),
										parameterWithName("code").description("redirect 후 받은 코드 값"),
										parameterWithName("state").description("카카오는 필요 없습니다.")
								),
								responseHeaders(
										headerWithName(HttpHeaders.AUTHORIZATION).description("accessToken"),
										headerWithName(HttpHeaders.SET_COOKIE).description("refreshToken")
								),
								responseFields(
										fieldWithPath("isMemberExist").description("멤버가 존재해서 true"),
										fieldWithPath("oauthUserId").description("빈값"),
										fieldWithPath("oauthType").description("빈값")
								)
						)
				);
	}

	@DisplayName("카카오를 사용해서 시작하고, 멤버가 존재하지 않을 때")
	@Test
	void authorizeKakaoAndMemberNotExists() throws Exception {
		//given
		given(oauthAuthorizeTypeFinder.findWithType(any()))
				.willReturn(new FakeMemberNotExistsOauthAuthorizePort());

		//when & then
		webTestClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/oauth/{oauthName}/authorize")
						.queryParam("redirectUri", "https://localhost:3000/naver/fallback")
						.queryParam("code", "code")
						.queryParam("state", "")
						.build("kakao"))
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.consumeWith(System.out::println)
				.consumeWith(document("auth/kakao/member-not-exists/success",
								queryParameters(
										parameterWithName("redirectUri").description("시작하기 눌렀을 때 넣어준 redirectUri"),
										parameterWithName("code").description("redirect 후 받은 코드 값"),
										parameterWithName("state").description("카카오는 필요 없습니다.")
								),
								responseFields(
										fieldWithPath("isMemberExist").description("신규 회원이라서 false"),
										fieldWithPath("oauthUserId").description("oauthUserId 추가 정보 받고 같이 넘겨줘야 합니다."),
										fieldWithPath("oauthType").description("oauthType 추가 정보 받고 같이 넘겨줘야 합니다.")
								)
						)
				);
	}


}