package com.example.FamGift.category.model;

import com.example.FamGift.user.model.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CATEGORY", uniqueConstraints = @UniqueConstraint(name = "UNIQUE_NAME_OWNER", columnNames = {"CATEGORY_NAME", "CATEGORY_OWNER_ID"}))
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;
    @Column(name = "CATEGORY_NAME", length = 20)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_OWNER_ID", foreignKey = @ForeignKey(name = "FK_CATEGORY_TO_USER"))
    private User categoryOwner;
    @CreationTimestamp
    @Column(name = "CATEGORY_CREATED_DATE")
    private LocalDateTime insertedDate;
    @UpdateTimestamp
    @Column(name = "CATEGORY_UPDATED_DATE")
    private LocalDateTime updatedDate;
}
