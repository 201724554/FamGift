package com.example.FamGift.common.mapper;

import lombok.Getter;

@Getter
public class KakaoUserInfoResponseEntity {
    private Long id;
    private KakaoUserInfoPropertiesEntity properties;

    @Getter
    public static class KakaoUserInfoPropertiesEntity {
        private String nickname;
    }
}
