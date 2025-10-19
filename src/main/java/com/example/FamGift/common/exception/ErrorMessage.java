package com.example.FamGift.common.exception;

public enum ErrorMessage {
    ELEM_NOT_FOUND("조건과 일치하는 데이터가 없습니다"),
    USER_ADMIN_NOT_MATCH("공유방 운영자가 아닙니다"),
    USER_NOT_FOUND("사용자가 존재하지 않습니다"),
    COUPON_ALREADY_USED("이미 사용처리된 쿠폰입니다"),
    COUPON_ALREADY_DELETED("이미 삭제처리된 쿠폰입니다"),
    COUPON_ALREADY_DISABLED("이미 미사용처리된 쿠폰입니다"),
    GROUP_MANAGED("관리하고 있는 공유방입니다"),
    NOT_ALLOWED("작업을 수행하기 위한 권한이 없습니다")
    ;

    private final String message;
    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
