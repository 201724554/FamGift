package com.example.FamGift.gifticon.controller;

import com.example.FamGift.common.controller.BaseController;
import com.example.FamGift.gifticon.dto.GifticonAddDto;
import com.example.FamGift.gifticon.facade.GifticonFacade;
import com.example.FamGift.gifticon.dto.GifticonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GifticonController implements BaseController {
    private final GifticonFacade gifticonFacade;

    public ResponseEntity<?> parseGifticon() {
        return null;
    }

    @GetMapping("/gifticon")
    public ResponseEntity<List<GifticonDto>> getGifticonByCategory(@RequestParam(value = "categories", required = false) Long categoryId) {
        List<GifticonDto> gifticonDtos = gifticonFacade.getGifticonByCategory(categoryId);
        return new ResponseEntity<>(gifticonDtos, HttpStatus.OK);
    }

    @GetMapping("/gifticon/{id}")
    public ResponseEntity<GifticonDto> getGifticonById(@PathVariable(value = "id", required = false) Long id) {
        GifticonDto gifticonDto = gifticonFacade.getGifticonById(id);
        return new ResponseEntity<>(gifticonDto, HttpStatus.OK);
    }

    @PostMapping("/gifticon")
    public ResponseEntity<?> addGifticon(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "couponInfo") GifticonAddDto dto
            ) {
        gifticonFacade.addGifticon(image, dto);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/gifticon/update")
    public ResponseEntity<?> updateGifticon(@RequestBody GifticonAddDto dto) {
        gifticonFacade.updateGifticon(dto);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/gifticon/delete")
    public ResponseEntity<?> deleteGifticon(@RequestBody GifticonDto dto) {
        gifticonFacade.deleteGifticon(dto.getId());
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/gifticon/use")
    public ResponseEntity<?> useGifticon(@RequestBody GifticonDto dto) {
        gifticonFacade.useGifticon(dto.getId());
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/gifticon/reuse")
    public ResponseEntity<?> reuseGifticon(@RequestBody GifticonDto dto) {
        gifticonFacade.reuseGifticon(dto.getId());
        return ResponseEntity.ok(null);
    }
}
