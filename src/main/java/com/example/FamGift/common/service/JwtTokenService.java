package com.example.FamGift.common.service;

import com.example.FamGift.common.model.JwtToken;

import java.util.Map;

public interface JwtTokenService {
    String makeToken(Object object);
    Map<String, String> makeClaims(Object object);
}
