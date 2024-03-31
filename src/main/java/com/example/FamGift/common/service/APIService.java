package com.example.FamGift.common.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

public interface APIService {
    ResponseEntity<?> sendRequest(String url, HttpMethod method, HttpEntity<?> entity, Class<?> responseType);
}
