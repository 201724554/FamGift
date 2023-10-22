package com.example.FamGift.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .addFilter(corsConfig.corsFilter())
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .csrf().disable()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .apply(authenticationFilterApply())
//                .and()
//                .apply(AuthorizationFilterApply.getInstance())
//                .and()
//                .logout().disable()
//                .authorizeHttpRequests(authorize -> authorize
//                        .mvcMatchers("/login/**","/user","/auth","/register/**","/logout","/device/**","/client/socket/**","/test/**",
//                                "/air-quality/**","/ocean-quality/**").permitAll()
//                        .mvcMatchers("/educating/**","/datafolder/**","/mydata/**","/dataupload/**",
//                                "/educator/inviteCode/generate","/student/join/**").permitAll()
//                        .mvcMatchers("/seed/**","/air-quality/mine", "/ocean-quality/mine","/user/**","/mydata/**").hasAnyRole("STUDENT","EDUCATOR","MANAGER","ADMIN")
//                        .mvcMatchers("/educator/**").hasAnyRole("EDUCATOR","MANAGER","ADMIN")
//                        .mvcMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
//                        .mvcMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().denyAll()
//                );
        return http.build();
    }
}
