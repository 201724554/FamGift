package com.example.FamGift.common.service;

import org.springframework.http.ResponseCookie;

public interface CookieService {
    ResponseCookie makeCookie(String name, Object item);
}
