package com.example.FamGift.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class APIServiceImpl implements APIService{

    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<?> sendRequest(String url, HttpMethod method, HttpEntity<?> entity, Class<?> responseType) {
        return restTemplate.exchange(
                url,
                method,
                entity,
                responseType
        );
    }
}
