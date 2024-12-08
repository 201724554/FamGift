package com.example.FamGift.common.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.example.FamGift.common.model.JwtToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenServiceImpl implements JwtTokenService {
    @Value("${jwt.subject}")
    private String subject;
    @Value("${jwt.key}")
    private String key;

    @Override
    public String makeToken(Object object) {
        Map<String, String> claims = makeClaims(object);
        JwtToken accessToken = new JwtToken(subject, new Date(9999, Calendar.DECEMBER,31), claims);

        return this.stringfy(accessToken);
    }

    public Map<String, String> makeClaims(Object object) {
        Map<String, String> claims = new HashMap<>();

        for(Method method : object.getClass().getMethods()) {
            String methodName = method.getName();
            if(methodName.startsWith("get") && !methodName.equals("getClass")) {
                String claimName = StringUtils.uncapitalize(methodName.substring(3));
                try {
                    claims.put(claimName, method.invoke(object).toString());
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    claims.put(claimName, null);
                }
            }
        }

        return claims;
    }

    public Map<String, Claim> getClaims(String jwt) {
        return JWT.require(Algorithm.HMAC512(key)).build().verify(jwt).getClaims();
    }

    private String stringfy(JwtToken jwtToken) {
        Map<String, String> claims = jwtToken.getClaims();

        JWTCreator.Builder jwtBuilder = JWT.create()
                .withSubject(jwtToken.getSubject())
                .withExpiresAt(jwtToken.getExpiresAt());

        claims.keySet().forEach((key) -> jwtBuilder.withClaim(key, claims.get(key)));

        return jwtBuilder.sign(Algorithm.HMAC512(key));
    }
}
