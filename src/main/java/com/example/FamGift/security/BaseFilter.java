package com.example.FamGift.security;

import com.example.FamGift.common.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class BaseFilter extends OncePerRequestFilter {
    private final JwtTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Map<String, String> cookieMap = new HashMap<>();

        if(!Objects.isNull(cookies)) {
            cookieMap = Arrays.stream(cookies).collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
        }

        if(!cookieMap.containsKey("jwt")) {
            log.info("no jwt");

            filterChain.doFilter(request, response);
            return;
        }

        PrincipalDetails principalDetails = null;
        try {
            log.info("jwt : " + cookieMap.get("jwt"));

            principalDetails = new PrincipalDetails(tokenService, cookieMap.get("jwt"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails,
                null,
                principalDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

       filterChain.doFilter(request, response);
    }
}
