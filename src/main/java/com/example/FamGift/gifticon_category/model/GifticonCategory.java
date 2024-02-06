package com.example.FamGift.gifticon_category.model;

import com.example.FamGift.category.model.Category;
import com.example.FamGift.common.model.CommonEntity;
import com.example.FamGift.gifticon.model.Gifticon;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_GIFTICON_CATEGORY", uniqueConstraints = @UniqueConstraint(name = "UNIQUE_GIFTICON_CATEGORY", columnNames = {"GIFTICON_ID", "CATEGORY_ID"}))
@Getter
public class GifticonCategory extends CommonEntity {
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
}
