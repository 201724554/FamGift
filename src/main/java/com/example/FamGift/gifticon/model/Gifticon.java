package com.example.FamGift.gifticon.model;

import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Gifticon {
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GIFTICON_OWNER_ID", foreignKey = @ForeignKey(name = "FK_GIFTICON_TO_USER"))
    private User gifticonOwner;
    @OneToOne
    @JoinColumn(name = "GIFTICON_GROUP", foreignKey = @ForeignKey(name = "FK_GIFTICON_TO_GROUP"))
    private Group group;
    @Column(name = "GIFTICON_EXPIRATION_DATE")
    private LocalDateTime expirationDate;
    @CreationTimestamp
    @Column(name = "GIFTICON_CREATED_DATE")
    private LocalDateTime insertedDate;
    @UpdateTimestamp
    @Column(name = "GIFTICON_UPDATE_DATE")
    private LocalDateTime updatedDate;
}
