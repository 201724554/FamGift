package com.example.FamGift.common.service;

import com.auth0.jwt.interfaces.Claim;
import com.example.FamGift.common.model.JwtToken;

import java.util.Map;

public interface JwtTokenService {
    String makeToken(Object object);
    Map<String, String> makeClaims(Object object);
    Map<String, Claim> getClaims(String jwt);
}
