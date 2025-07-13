package com.example.FamGift.gifticon.model;

import com.example.FamGift.common.model.CommonEntity;
import com.example.FamGift.common.model.CommonYn;
import com.example.FamGift.gifticon.dto.GifticonAddDto;
import com.example.FamGift.gifticon_category.model.GifticonCategory;
import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Table(name = "TB_GIFTICON", 
        uniqueConstraints ={
            @UniqueConstraint(name = "UNIQUE_OWNER_GROUP", columnNames = {"GIFTICON_OWNER_ID", "GIFTICON_GROUP"})
})
@Getter
public class Gifticon extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFTICON_ID")
    private Long id;
    @Column(name = "GIFTICON_IMAGE_PATH")
    private String imagePath;
    @Column(name = "GIFTICON_BRAND", length = 30)
    private String brand;
    @Column(name = "GIFTICON_NAME", length = 40)
    private String name;
    @Column(name = "GIFTICON_BARCODE", length = 30)
    private String barcode;
    @Column(name = "GIFTICON_PRICE")
    private Integer price;
    @Column(name = "GIFTICON_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private GifticonType gifticonType;
    @Column(name = "GIFTICON_IS_USED", length = 1)
    @Enumerated(EnumType.STRING)
    private CommonYn gifticonIsUsed;
    @Column(name = "GIFTICON_USE_DATE")
    private LocalDateTime gifticonUsedDate;
    @Column(name = "GIFTICON_USE_YN", length = 1)
    @Enumerated(EnumType.STRING)
    private CommonYn gifticonUseYn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GIFTICON_OWNER_ID", foreignKey = @ForeignKey(name = "FK_GIFTICON_TO_USER"))
    private User gifticonOwner;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GIFTICON_GROUP", foreignKey = @ForeignKey(name = "FK_GIFTICON_TO_GROUP"))
    private Group group;
    @Column(name = "GIFTICON_EXPIRATION_DATE")
    private LocalDate expirationDate;
    @OneToMany(mappedBy = "gifticon")
    private List<GifticonCategory> gifticonCategories;

    public Gifticon() {}
    public Gifticon(Long id) {
        this.id = id;
    }
    public Gifticon(GifticonAddDto dto, User user, String imagePath) {
        if(dto.getId() != null) {
            this.id = dto.getId();
        }
        this.barcode = dto.getBarcode();
        this.brand = dto.getBrand();
        this.name = dto.getProductName();
        this.gifticonOwner = user;
        this.expirationDate = dto.getExpirationDate();
        this.imagePath = imagePath;
        this.gifticonIsUsed = CommonYn.N;
        this.gifticonUseYn = CommonYn.Y;
    }
    public Gifticon(GifticonAddDto dto, User user, String imagePath, CommonYn gifticonIsUsed, CommonYn gifticonUseYn) {
        if(dto.getId() != null) {
            this.id = dto.getId();
        }
        this.barcode = dto.getBarcode();
        this.brand = dto.getBrand();
        this.name = dto.getProductName();
        this.gifticonOwner = user;
        this.expirationDate = dto.getExpirationDate();
        this.imagePath = imagePath;
        this.gifticonIsUsed = gifticonIsUsed;
        this.gifticonUseYn = gifticonUseYn;
    }

    public void delete() {
        this.gifticonUseYn = CommonYn.N;
    }

    public void use() {
        this.gifticonIsUsed = CommonYn.Y;
        this.gifticonUsedDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public void reuse() {
        this.gifticonIsUsed = CommonYn.N;
    }

    public void update(GifticonAddDto dto, List<GifticonCategory> gifticonCategories) {
        this.barcode = dto.getBarcode();
        this.brand = dto.getBrand();
        this.name = dto.getProductName();
        this.expirationDate = dto.getExpirationDate();
        this.gifticonCategories.clear();
        this.gifticonCategories = gifticonCategories;
    }
}
