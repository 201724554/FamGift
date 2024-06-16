package com.example.FamGift.common.facade;

import com.example.FamGift.common.dto.KakaoLoginDto;
import com.example.FamGift.common.service.APIService;
import com.example.FamGift.common.service.CookieService;
import com.example.FamGift.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class LoginFacadeImpl implements LoginFacade {
    private final APIService apiService;
    private final CookieService cookieService;
    private final UserService userService;

    @Value("${kakao.token_url}")
    private String kakaoTokenUrl;
    @Value("${kakao.client_id}")
    private String kakaoClientId;
    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

    @Override
    public Cookie kakaoLogin(KakaoLoginDto kakaoLoginDto) {
        /**
         * 1. 넘어온 code 값으로 kakao api 호출해 token 발행
         * 2. token 이용해 사용자 정보 불러오는 api 호출
         * 3.1 사용자 정보가 user 테이블에 존재하면 쿠키 생성 후 리턴
         * 3.2 사용자 정보가 없으면 사용자 신규 생성 후 쿠기 생성 후 리턴
         **/
        String code = kakaoLoginDto.getCode();

        HttpHeaders header = apiService.makeHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String,String> params = apiService.makeParam(
                "grant_type","authorization_code",
                "client_id", kakaoClientId,
                "redirect_uri", redirect_uri,
                "code", code
        );

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, header);

        ResponseEntity<?> kakaoTokenResponse = apiService.sendRequest(kakaoTokenUrl, HttpMethod.POST, kakaoTokenRequest, String.class);
        return null;
    }
}
