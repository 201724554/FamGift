package com.example.FamGift.group.model;

import com.example.FamGift.user.model.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_GROUP", uniqueConstraints = @UniqueConstraint(name = "UNIQUE_ADMIN_NAME", columnNames = {"GROUP_ADMIN_ID", "GROUP_NAME"}))
@Getter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ADMIN_ID", foreignKey = @ForeignKey(name = "FK_GROUP_TO_USER"))
    private User groupAdmin;
    @Column(name = "GROUP_PASSWORD")
    private String groupPassword;
    @Column(name = "GROUP_NAME", length = 20)
    private String groupName;
    @CreationTimestamp
    @Column(name = "GROUP_CREATED_DATE")
    private LocalDateTime insertedDate;
    @UpdateTimestamp
    @Column(name = "GROUP_UPDATED_DATE")
    private LocalDateTime updatedDate;
}
