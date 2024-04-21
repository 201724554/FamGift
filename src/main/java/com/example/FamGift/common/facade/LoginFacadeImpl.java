package com.example.FamGift.common.facade;

import com.example.FamGift.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginFacadeImpl implements LoginFacade {
    private final UserService userService;
    @Override
    public Cookie kakaoLogin() {
        return null;
    }
}
