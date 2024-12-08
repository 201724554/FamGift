package com.example.FamGift.common.facade;

import com.example.FamGift.common.dto.KakaoLoginDto;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginFacade extends BaseFacade {
    //ResponseCookie kakaoLogin(KakaoLoginDto dto);
    Map<String, ResponseCookie> kakaoLogin(KakaoLoginDto dto);
}
