package com.example.FamGift.gifticon.dto;

import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.gifticon.model.GifticonType;
import com.example.FamGift.gifticon.model.GifticonUseYn;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GifticonDto {
    private Long id;
    private String imagePath;
    private String brand;
    private String name;
    private String barcode;
    private Integer price;
    private GifticonType gifticonType;
    private GifticonUseYn gifticonUseYn;
    private LocalDate expirationDate;

    public GifticonDto() {}
    public GifticonDto(Gifticon gifticon) {
        this.id = gifticon.getId();
        this.imagePath = gifticon.getImagePath();
        this.name = gifticon.getName();
        this.barcode = gifticon.getBarcode();
        this.brand = gifticon.getBrand();
        this.price = gifticon.getPrice();
        this.gifticonType = gifticon.getGifticonType();
        this.gifticonUseYn = gifticon.getGifticonUseYn();
        this.expirationDate = gifticon.getExpirationDate();
    }
}
