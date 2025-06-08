package com.example.FamGift.gifticon.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
public class GifticonAddDto {
    private List<Long> categories;
    private String barcode;
    private String productName;
    private String brand;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
}
