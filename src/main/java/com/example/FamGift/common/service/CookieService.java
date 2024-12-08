package com.example.FamGift.common.service;

import com.example.FamGift.common.config.CookieOption;
import org.springframework.http.ResponseCookie;

public interface CookieService {
    ResponseCookie makeCookie(String name, Object item, CookieOption option);
}
