package com.example.FamGift.gifticon_category.model;

import com.example.FamGift.category.model.Category;
import com.example.FamGift.gifticon.model.Gifticon;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class GifticonCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFTICON_CATEGORY_ID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GIFTICON_ID", foreignKey = @ForeignKey(name = "FK_GIFTICON_CATEGORY_TO_GIFTICON"))
    private Gifticon gifticon;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_GIFTICON_CATEGORY_TO_CATEGORY"))
    private Category category;
    @CreationTimestamp
    @Column(name = "GIFTICON_CATEGORY_CREATED_DATE")
    private LocalDateTime insertedDate;
    @UpdateTimestamp
    @Column(name = "GIFTICON_CATEGORY_UPDATE_DATE")
    private LocalDateTime updatedDate;
}
