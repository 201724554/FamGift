package com.example.FamGift.gifticon.controller;

import com.example.FamGift.common.controller.BaseController;
import com.example.FamGift.gifticon.dto.GifticonAddDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GifticonController implements BaseController {

    public ResponseEntity<?> parseGifticon() {
        return null;
    }

    @PostMapping("/gifticon")
    public ResponseEntity<?> addGifticon(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "couponInfo") GifticonAddDto dto
            ) {

        return ResponseEntity.ok(null);
    }
}
