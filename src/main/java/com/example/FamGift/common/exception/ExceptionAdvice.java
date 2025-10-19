package com.example.FamGift.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<String> handleCustomException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<String> handelNoSuchElementException(Exception e) {
        if (e.getMessage() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("조건과 일치하는 데이터가 없습니다");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
