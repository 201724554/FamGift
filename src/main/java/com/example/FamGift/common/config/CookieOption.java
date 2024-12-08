package com.example.FamGift.common.config;

import lombok.Getter;

import java.time.Duration;

@Getter
public class CookieOption {
    //private final String domain;
    private final String sameSite;
    private final boolean httpOnly;
    private final boolean secure;
    private final String path;
    private final Duration maxAge;

    public CookieOption(String sameSite, boolean httpOnly, boolean secure, String path, Duration maxAge) {
        //this.domain = domain;
        this.sameSite = sameSite;
        this.httpOnly = httpOnly;
        this.secure = secure;
        this.path = path;
        this.maxAge = maxAge;
    }
}
