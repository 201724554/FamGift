package com.example.FamGift.common.facade;

import org.springframework.boot.web.server.Cookie;

public interface LoginFacade extends BaseFacade {
    Cookie kakaoLogin();
}
