package com.example.FamGift.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity(debug = false)
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsFilter corsFilter;
    private final BaseFilter baseFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .addFilter(corsFilter)
                .addFilterAfter(baseFilter, CorsFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .authorizeHttpRequests(authorize -> {
                            authorize
                                    .mvcMatchers("/api/**").hasAnyRole("USER")
                                    .mvcMatchers("/login/test").hasAnyRole("USER")
                                    .mvcMatchers("/login").permitAll();
                        }
                )
        ;
        return security.build();
    }
}
