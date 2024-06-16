package com.example.FamGift.common.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.net.http.HttpResponse;

public interface APIService {
    HttpHeaders makeHeader(String ...data);
    MultiValueMap<String, String> makeParam(String ...data);
    ResponseEntity<?> sendRequest(String url, HttpMethod method, HttpEntity<?> entity, Class<?> responseType);
}
