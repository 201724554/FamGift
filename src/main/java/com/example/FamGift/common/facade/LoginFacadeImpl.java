package com.example.FamGift.common.facade;

import com.example.FamGift.common.config.CookieOption;
import com.example.FamGift.common.dto.KakaoLoginDto;
import com.example.FamGift.common.mapper.KakaoTokenResponseEntity;
import com.example.FamGift.common.mapper.KakaoUserInfoResponseEntity;
import com.example.FamGift.common.service.APIService;
import com.example.FamGift.common.service.CookieService;
import com.example.FamGift.common.service.JwtTokenService;
import com.example.FamGift.user.model.Auth;
import com.example.FamGift.user.model.User;
import com.example.FamGift.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@Component
@RequiredArgsConstructor
@Slf4j
public class LoginFacadeImpl implements LoginFacade {
    private final APIService apiService;
    private final CookieService cookieService;
    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${kakao.token_uri}")
    private String kakaoTokenUri;
    @Value("${kakao.client_id}")
    private String kakaoClientId;
    @Value("${kakao.redirect_uri}")
    private String redirect_uri;
    @Value("${kakao.user_info_uri}")
    private String kakaoUserInfoUri;
    @Value("${user.pwd.kakao}")
    private String kakaoDefaultPwd;


    @Override
    @Transactional
    public Map<String, ResponseCookie> kakaoLogin(KakaoLoginDto kakaoLoginDto) {
        /**
         * 1. 넘어온 code 값으로 kakao api 호출해 token 발행
         * 2. token 이용해 사용자 정보 불러오는 api 호출
         * 3.1 사용자 정보가 user 테이블에 존재하면 쿠키 생성 후 리턴
         * 3.2 사용자 정보가 없으면 사용자 신규 생성 후 쿠기 생성 후 리턴
         **/
        Map<String, ResponseCookie> mp = new HashMap<>();

        String code = kakaoLoginDto.getCode();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,String> params = apiService.makeParam(
                "grant_type","authorization_code",
                "client_id", kakaoClientId,
                "redirect_uri", redirect_uri,
                "code", code
        );

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, header);
        ResponseEntity<?> kakaoTokenResponse = apiService.sendRequest(kakaoTokenUri, HttpMethod.POST, kakaoTokenRequest, KakaoTokenResponseEntity.class);
        KakaoTokenResponseEntity kakaoTokenResponseEntity = (KakaoTokenResponseEntity) kakaoTokenResponse.getBody();

        header.add("Authorization", "Bearer " + kakaoTokenResponseEntity.getAccess_token());
        HttpEntity<?> kakaoUserInfoRequest = new HttpEntity<>(null, header);
        ResponseEntity<?> kakaoUserInfoResponse = apiService.sendRequest(kakaoUserInfoUri, HttpMethod.GET, kakaoUserInfoRequest, KakaoUserInfoResponseEntity.class);

        KakaoUserInfoResponseEntity kakaoUserInfoResponseEntity = (KakaoUserInfoResponseEntity) kakaoUserInfoResponse.getBody();

        if(!Objects.isNull(kakaoUserInfoResponseEntity)) {
            log.info("kakao user info : " + kakaoUserInfoResponseEntity.toString());
        }

        Long userId = kakaoUserInfoResponseEntity.getId();
        String userNickname = kakaoUserInfoResponseEntity.getProperties().getNickname();
        Optional<User> user = userService.findUserByUserId(userId);
        User newUser = null;

        if(user.isEmpty()) {
            log.info("empty user");

            newUser = new User(userId, userNickname, bCryptPasswordEncoder.encode(kakaoDefaultPwd), Auth.ROLE_USER);
            userService.saveUser(newUser);
        } else {
            log.info("not empty user");
            newUser = user.get();
        }

        String accessToken = jwtTokenService.makeToken(newUser);

        mp.put("jwt", cookieService.makeCookie("jwt", accessToken, new CookieOption("Strict", true, false, "/", Duration.ofHours(9999))));
        //mp.put("userId", cookieService.makeCookie("userId", userId, new CookieOption("Strict", false, false, "/", Duration.ofHours(9999))));
        mp.put("login", cookieService.makeCookie("login", "login", new CookieOption("Strict", false, false, "/", Duration.ofHours(9999))));
        return mp;
    }
}
