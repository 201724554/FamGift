package com.example.FamGift.common.service;

import com.auth0.jwt.interfaces.Claim;
import com.example.FamGift.common.exception.CustomException;
import com.example.FamGift.common.exception.ErrorMessage;
import com.example.FamGift.user.model.User;
import com.example.FamGift.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommonService {
    private final JwtTokenService tokenService;
    private final UserRepository userRepository;

    public String getCookieValueFromRequest(String cookieName) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (!Objects.isNull(attributes)) {
            HttpServletRequest request = attributes.getRequest();
            Cookie[] cookies = request.getCookies();
            if (!Objects.isNull(cookies)) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(cookieName)) {
                        return cookie.getValue();
                    }
                }
            }
        }
        return null; // 해당 쿠키가 없으면 null 반환
    }

    public User findUserByJwtToken(String token) {
        Map<String, Claim> claims = tokenService.getClaims(token);
        Long userId = Long.valueOf(claims.get("id").asString());
        Optional<User> optionalUser = userRepository.findUserById(userId);
        return optionalUser.orElseThrow(() -> new CustomException(ErrorMessage.USER_NOT_FOUND));
    }

    public User findUserByJwtToken() {
        String jwt = getCookieValueFromRequest("jwt");
        return findUserByJwtToken(jwt);
    }

    public String makeShortUuid() {
        String uuidString = UUID.randomUUID().toString();
        System.out.print(uuidString + " -> ");
        byte[] uuidStringBytes = uuidString.getBytes(StandardCharsets.UTF_8);
        byte[] hashBytes;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            hashBytes = messageDigest.digest(uuidStringBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 4; j++) {
            sb.append(String.format("%02x", hashBytes[j]));
        }
        return sb.toString();
    }
}
