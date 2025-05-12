package com.example.FamGift.common.controller;

import com.example.FamGift.common.dto.KakaoLoginDto;
import com.example.FamGift.common.facade.LoginFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginControllerImpl implements LoginController {
    private final LoginFacade loginFacade;
    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody KakaoLoginDto kakaoLoginDto) {
        Map<String, ResponseCookie> cookies = loginFacade.kakaoLogin(kakaoLoginDto);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookies.get("jwt").toString())
                .header(HttpHeaders.SET_COOKIE, cookies.get("login").toString()) //이 부분 userId에서 그냥 의미없는 값(login 여부)으로 변경
                .build();
    }
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        log.info("test");
        return ResponseEntity
                .ok()
                .build();
    }
}
