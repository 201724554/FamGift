package com.example.FamGift;

import com.example.FamGift.common.service.JwtTokenService;
import com.example.FamGift.common.service.JwtTokenServiceImpl;
import com.example.FamGift.user.model.Auth;
import com.example.FamGift.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@Slf4j
@SpringBootTest
public class JwtTokenTest {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Test
    public void makeJwtTokenClaims() {
        User user = new User(123L, "TEST", "TEST", Auth.NORMAL);
        Map<String, String> claims = jwtTokenService.makeClaims(user);
        log.info(claims.toString());
    }

    @Test
    public void makeToken() {
        User user = new User(123L, "TEST", "TEST", Auth.NORMAL);
        String token = jwtTokenService.makeToken(user);
        log.info(token);
    }
}
