package com.example.FamGift.common.facade;

import com.example.FamGift.common.dto.BaseDto;
import com.example.FamGift.common.dto.KakaoLoginDto;
import org.springframework.boot.web.server.Cookie;

public interface LoginFacade extends BaseFacade {
    Cookie kakaoLogin(KakaoLoginDto dto);
}
