package com.example.FamGift;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.example.FamGift.common.service.JwtTokenService;
import com.example.FamGift.common.service.JwtTokenServiceImpl;
import com.example.FamGift.user.model.Auth;
import com.example.FamGift.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@Slf4j
@SpringBootTest
public class JwtTokenTest {
    @Value("${jwt.subject}")
    private String subject;
    @Value("${jwt.key}")
    private String key;
    @Autowired
    private JwtTokenService jwtTokenService;

    @Test
    @Order(1)
    public void makeJwtTokenClaims() {
        User user = new User(123L, "TEST", "TEST", Auth.ROLE_USER);
        Map<String, String> claims = jwtTokenService.makeClaims(user);
        log.info(claims.toString());
    }

    @Test
    @Order(2)
    public void makeToken() {
        User user = new User(123L, "TEST", "TEST", Auth.ROLE_USER);
        String token = jwtTokenService.makeToken(user);
        log.info(token);
    }

    @Test
    @Order(3)
    public void getClaims() {
        User user = new User(123L, "TEST", "TEST", Auth.ROLE_USER);
        String token = jwtTokenService.makeToken(user);
        log.info(token);
        String jwt  = JWT.require(Algorithm.HMAC512(key)).build().verify(token).getClaims().toString();
       // String jwt2  = JWT.require(Algorithm.HMAC512(key)).build().verify(token).getClaims().get("claimName").toString();
        log.info(jwt);
        //log.info(jwt2);
    }

    @Test
    @Order(4)
    public void getClaims2() {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyIiOiJjb20uZXhhbXBsZS5GYW1HaWZ0LnVzZXIubW9kZWwuVXNlckA3MDcwNTEwZCIsInN1YiI6ImZhbWdpZnQiLCJleHAiOjMxMzM2MDQxMjQwMH0.0tK5RjtKutgPBD--QRriqJi5vKqEpe-yEi4SEDLKCI36EU00chm0aeN40uWxgF28-HkRjRA_cqqpQsIWKuUgwA";
        Map<String, Claim> claims = jwtTokenService.getClaims(jwt);
        log.info(claims.keySet().toString());
    }

    @Test
    @Order(5)
    public void enumTest() {
        log.info(Auth.valueOf("ROLE_USER").toString());
    }
}
