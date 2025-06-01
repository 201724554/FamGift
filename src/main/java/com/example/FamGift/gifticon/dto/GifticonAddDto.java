package com.example.FamGift.gifticon.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GifticonAddDto {
    private List<Long> categories;
    private String barcode;
    private String productName;
    private String brand;
    //private LocalDateTime expirationDate;
}
