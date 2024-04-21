package com.example.FamGift.common.controller;

import com.example.FamGift.common.facade.LoginFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {
    private final LoginFacade loginFacade;

    @PostMapping("/kakao/login")
    public ResponseEntity<String> kakaoLogin() {
        Cookie cookie = loginFacade.kakaoLogin();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
