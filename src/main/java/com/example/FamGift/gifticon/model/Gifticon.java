package com.example.FamGift.gifticon.model;

import com.example.FamGift.common.model.CommonEntity;
import com.example.FamGift.gifticon.dto.GifticonAddDto;
import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_GIFTICON", 
        uniqueConstraints ={
            @UniqueConstraint(name = "UNIQUE_IMAGE_PATH", columnNames = {"GIFTICON_IMAGE_PATH"}),
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
    @Column(name = "GIFTICON_USE_YN", length = 1)
    @Enumerated(EnumType.STRING)
    private GifticonUseYn gifticonUseYn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GIFTICON_OWNER_ID", foreignKey = @ForeignKey(name = "FK_GIFTICON_TO_USER"))
    private User gifticonOwner;
    @OneToOne
    @JoinColumn(name = "GIFTICON_GROUP", foreignKey = @ForeignKey(name = "FK_GIFTICON_TO_GROUP"))
    private Group group;
    @Column(name = "GIFTICON_EXPIRATION_DATE")
    private LocalDate expirationDate;

    public Gifticon() {}
    public Gifticon(GifticonAddDto dto, User user, String imagePath) {
        this.barcode = dto.getBarcode();
        this.brand = dto.getBrand();
        this.name = dto.getProductName();
        this.gifticonOwner = user;
        this.expirationDate = dto.getExpirationDate();
        this.imagePath = imagePath;
        this.gifticonUseYn = GifticonUseYn.Y;
    }
    public Gifticon(GifticonAddDto dto, User user, String imagePath, GifticonUseYn gifticonUseYn) {
        this.barcode = dto.getBarcode();
        this.brand = dto.getBrand();
        this.name = dto.getProductName();
        this.gifticonOwner = user;
        this.expirationDate = dto.getExpirationDate();
        this.imagePath = imagePath;
        this.gifticonUseYn = gifticonUseYn;
    }

    public void delete() {
        this.gifticonUseYn = GifticonUseYn.N;
    }
}
