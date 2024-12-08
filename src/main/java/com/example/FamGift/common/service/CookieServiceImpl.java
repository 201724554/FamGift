package com.example.FamGift.common.service;

import com.example.FamGift.common.config.CookieOption;
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
    public ResponseCookie makeCookie(String name, Object item, CookieOption option) {
        ResponseCookie.ResponseCookieBuilder responseCookieBuilder = ResponseCookie.from(name, item.toString());
        setDefault(responseCookieBuilder, option);
        return responseCookieBuilder.build();
    }

    private void setDefault(ResponseCookie.ResponseCookieBuilder builder, CookieOption option) {
        if (runtimeProfile.equals("local") || runtimeProfile.equals("dev")) {
            builder
                    .domain("localhost")
                    .sameSite(option.getSameSite())
                    .secure(option.isSecure());
        } else if (runtimeProfile.equals("prod")) {
            builder
                    .domain(domain)
                    .sameSite(option.getSameSite())
                    .secure(option.isSecure());
        }
        builder
                .httpOnly(option.isHttpOnly())
                .path(option.getPath())
                .maxAge(option.getMaxAge());
    }

    private void setDefault(ResponseCookie.ResponseCookieBuilder builder, boolean httpOnly) {
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
                .httpOnly(httpOnly)
                .path("/")
                .maxAge(Duration.ofHours(9999));
    }
}
