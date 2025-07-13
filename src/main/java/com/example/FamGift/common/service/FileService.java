package com.example.FamGift.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileService {
    @Value("${file.empty}")
    private String defaultEmptyPath;
    @Value("${file.url}")
    private String fileDefaultPath;

    public byte[] getImageFile(String imageUrl) {
        try {
            // 이미지 파일 경로 설정 (예제: src/main/resources/static/images/)
            File imageFile = new File(fileDefaultPath + imageUrl);
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            byte[] imageBytes = fileInputStream.readAllBytes();
            fileInputStream.close();

            return imageBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFilePath(MultipartFile image) {
        if(image == null) {
            //빈 이미지 경로로 대체
            return defaultEmptyPath;
        }
        String uuid = UUID.randomUUID().toString();
        return uuid + "_" + image.getOriginalFilename();
    }

    public void uploadFile(MultipartFile image, String filepath) {
        try {
            image.transferTo(new File(fileDefaultPath + filepath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
