package com.example.FamGift.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CookieServiceImpl implements CookieService {
    @Value("${spring.profiles.active}")
    private String runtimeProfile;
    @Value("${server.domain}")
    private String domain;

    @Override
    public ResponseCookie makeCookie(String name, Object item) {
        ResponseCookie.ResponseCookieBuilder responseCookieBuilder = ResponseCookie.from(name, item.toString());
        setDefault(responseCookieBuilder);
        return responseCookieBuilder.build();
    }

    private void setDefault(ResponseCookie.ResponseCookieBuilder builder) {
        if (runtimeProfile.equals("local") || runtimeProfile.equals("dev")) {
            builder
                    .domain("localhost")
                    .sameSite("Strict")
                    .secure(false);
        } else if (runtimeProfile.equals("prod")) {
            builder
                    .domain(domain)
                    .sameSite("Strict")
                    .secure(true);
        }
        builder
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofHours(9999));
    }
}
