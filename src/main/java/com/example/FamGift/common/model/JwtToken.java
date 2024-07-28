package com.example.FamGift.common.model;

import lombok.Getter;

import java.util.Date;
import java.util.Map;

@Getter
public class JwtToken {
    private final String subject;
    private final Date expiresAt;
    private final Map<String, String> claims;

    public JwtToken(String subject, Date expiresAt, Map<String, String> claims) {
        this.subject = subject;
        this.expiresAt = expiresAt;
        this.claims = claims;
    }
}
