package com.example.FamGift.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class APIServiceImpl implements APIService {

    private final RestTemplate restTemplate;

    /**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       3
     * 문자 배열로 header1, value1, header2, value2 형식으로 넣으면 순서에 맞춰서 헤더 생성
     **/
    @Override
    public HttpHeaders makeHeader(String ...data) throws IllegalArgumentException {
        if(data.length % 2 != 0) {
            throw new IllegalArgumentException("올바르지 않은 파라미터 형식: [header1, value1, header2, value2...] 형식 필요");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        int index = 0;
        while(index < data.length) {
            httpHeaders.add(data[index], data[index+1]);
            index += 2;
        }

        return httpHeaders;
    }

    /**
     * 문자 배열로 header1, value1, header2, value2 형식으로 넣으면 순서에 맞춰서 헤더 생성
     **/
    @Override
    public MultiValueMap<String, String> makeParam(String ...data) throws IllegalArgumentException {
        if(data.length % 2 != 0) {
            throw new IllegalArgumentException("올바르지 않은 파라미터 형식: [header1, value1, header2, value2...] 형식 필요");
        }

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        int index = 0;
        while(index < data.length) {
            params.add(data[index], data[index+1]);
            index += 2;
        }
        return params;
    }

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
