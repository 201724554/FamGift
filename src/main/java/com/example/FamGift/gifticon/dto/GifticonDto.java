package com.example.FamGift.gifticon.dto;

import com.example.FamGift.common.model.CommonYn;
import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.gifticon.model.GifticonType;
import com.example.FamGift.gifticon_category.model.GifticonCategory;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GifticonDto {
    private Long id;
    private String imagePath;
    private String brand;
    private String name;
    private String barcode;
    private Integer price;
    private GifticonType gifticonType;
    private LocalDateTime gifticonUsedDate;
    private CommonYn gifticonIsUsed;
    private CommonYn gifticonUseYn;
    private LocalDate expirationDate;
    private List<Long> categoryIds;

    public GifticonDto() {}
    public GifticonDto(Gifticon gifticon) {
        this.id = gifticon.getId();
        this.imagePath = gifticon.getImagePath();
        this.name = gifticon.getName();
        this.barcode = gifticon.getBarcode();
        this.brand = gifticon.getBrand();
        this.price = gifticon.getPrice();
        this.gifticonType = gifticon.getGifticonType();
        this.gifticonIsUsed = gifticon.getGifticonIsUsed();
        this.gifticonUseYn = gifticon.getGifticonUseYn();
        this.expirationDate = gifticon.getExpirationDate();
        this.gifticonUsedDate = gifticon.getGifticonUsedDate();
    }
    public void addCategories(Gifticon gifticon) {
        this.categoryIds = gifticon.getGifticonCategories().stream().map(elem -> elem.getCategory().getId()).collect(Collectors.toList());
    }
}
