package com.example.FamGift.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
    private final Testfilter testFilter;
    private final Testfilter2 testFilter2;
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
//        return config.getAuthenticationManager();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .addFilter(corsFilter)
                .addFilterAfter(baseFilter, CorsFilter.class)
                .addFilterAfter(testFilter, BaseFilter.class)
                .addFilterBefore(testFilter2, BaseFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .authorizeHttpRequests(authorize -> {
                            authorize
                                    .mvcMatchers("/api/**").permitAll()
                                    //.mvcMatchers("/api/**").hasAnyRole("USER")
                                    .mvcMatchers("/login").permitAll();
                        }
//                        authorize.mvcMatchers("/login").permitAll()
//                            .mvcMatchers("/api/**").hasAnyRole("USER")
                )
        ;
        return security.build();
    }
}
