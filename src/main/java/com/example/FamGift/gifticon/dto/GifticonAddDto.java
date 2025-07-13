package com.example.FamGift.gifticon.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
public class GifticonAddDto {
    private Long id;
    private List<Long> categories;
    private String barcode;
    private String productName;
    private String brand;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    @Override
    public String toString() {
        return  "barcode: " + barcode + "\n" +
                "productName: " + productName + "\n" +
                "brand: " + brand + "\n" +
                "categories: " + categories.size();
    }
}
