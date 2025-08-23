package com.example.FamGift.gifticon.dto;

import com.example.FamGift.common.model.CommonYn;
import com.example.FamGift.gifticon.model.Gifticon;
import com.example.FamGift.gifticon.model.GifticonType;
import com.example.FamGift.group.model.Group;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GifticonGroupDto {
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
    private Long groupId;
    private String groupName;
    private Long groupAdminId;
    private String groupAdminName;
    private Long gifticonOwnerId;

    public GifticonGroupDto(Gifticon gifticon, Group group) {
        this.id = gifticon.getId();
        this.imagePath = gifticon.getImagePath();
        this.brand = gifticon.getBrand();
        this.name = gifticon.getName();
        this.barcode = gifticon.getBarcode();
        this.price = gifticon.getPrice();
        this. gifticonType = gifticon.getGifticonType();
        this.gifticonUsedDate = gifticon.getGifticonUsedDate();
        this.gifticonIsUsed = gifticon.getGifticonIsUsed();
        this.gifticonUseYn = gifticon.getGifticonUseYn();
        this.expirationDate = gifticon.getExpirationDate();
        this.groupId = group.getId();
        this.groupName = group.getGroupName();
        this.groupAdminId = group.getGroupAdmin().getId();
        this.groupAdminName = group.getGroupAdmin().getName();
    }
    public GifticonGroupDto(
            Long id,
            String imagePath,
            String brand,
            String name,
            String barcode,
            Integer price,
            GifticonType gifticonType,
            LocalDateTime gifticonUsedDate,
            CommonYn gifticonIsUsed,
            CommonYn gifticonUseYn,
            LocalDate expirationDate,
            Long gifticonOwnerId,

            Long groupId,
            String groupName,
            Long groupAdminId,
            String groupAdminName
    ) {
        this.id = id;
        this.imagePath = imagePath;
        this.brand = brand;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.gifticonType = gifticonType;
        this.gifticonUsedDate = gifticonUsedDate;
        this.gifticonIsUsed = gifticonIsUsed;
        this.gifticonUseYn = gifticonUseYn;
        this.expirationDate = expirationDate;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupAdminId = groupAdminId;
        this.groupAdminName = groupAdminName;
        this.gifticonOwnerId = gifticonOwnerId;
    }
}
