package com.example.FamGift.gifticon.controller;

import com.example.FamGift.common.controller.BaseController;
import com.example.FamGift.gifticon.dto.GifticonAddDto;
import com.example.FamGift.gifticon.facade.GifticonFacade;
import com.example.FamGift.gifticon.model.GifticonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GifticonController implements BaseController {
    private final GifticonFacade gifticonFacade;

    public ResponseEntity<?> parseGifticon() {
        return null;
    }

    @GetMapping("/gifticon")
    public ResponseEntity<List<GifticonDto>> getGifticon() {
        List<GifticonDto> gifticonDtos = gifticonFacade.getGifticon();
        return new ResponseEntity<>(gifticonDtos, HttpStatus.OK);
    }

    @PostMapping("/gifticon")
    public ResponseEntity<?> addGifticon(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "couponInfo") GifticonAddDto dto
            ) {
        gifticonFacade.addGifticon(image, dto);
        return ResponseEntity.ok(null);
    }
}
