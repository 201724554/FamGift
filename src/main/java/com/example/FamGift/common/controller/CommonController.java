package com.example.FamGift.common.controller;

import com.example.FamGift.common.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommonController implements BaseController {
    private final FileService fileService;

    @GetMapping("/image/{imageUrl}")
    public ResponseEntity<byte[]> getImage(@PathVariable("imageUrl") String imageUrl) {
        byte[] file = fileService.getImageFile(imageUrl);
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Content-Type", "image/*"); // 필요하면 다른 타입 사용 가능
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }
}
