package com.example.FamGift.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;

@Configuration
public class CorsConfig {
    @Value("${build.mode}")
    private String opMode;
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        if(opMode.equals("prod")) {
            config.addAllowedOriginPattern("https://famgift.site");
        }
        else if(opMode.equals("dev") || opMode.equals("local")) {
            config.addAllowedOriginPattern("http://localhost:3000");
        }

        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
