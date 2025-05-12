package com.example.FamGift.security;

import com.auth0.jwt.interfaces.Claim;
import com.example.FamGift.common.service.JwtTokenService;
import com.example.FamGift.user.model.Auth;
import com.example.FamGift.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Slf4j
public class PrincipalDetails implements UserDetails {
    private final User user;
    public PrincipalDetails(JwtTokenService tokenService, String jwt) {
        Map<String, Claim> claims = tokenService.getClaims(jwt);

        log.info("claims : " + claims.keySet());
        log.info("claim values : " + claims);

        this.user = new User(
                Long.getLong(claims.get("id").asString()),
                claims.get("name").asString(),
                null,
                claims.get("authority").asString()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> user.getAuthority().toString());
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

