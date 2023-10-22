package com.example.FamGift.user_group.model;

import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER_GROUP")
@Getter
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_GROUP_ID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_GROUP_TO_USER"))
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_USER_GROUP_TO_GROUP"))
    private Group group;
    @CreationTimestamp
    @Column(name = "USER_GROUP_CREATED_DATE")
    private LocalDateTime insertedDate;
    @UpdateTimestamp
    @Column(name = "USER_GROUP_UPDATED_DATE")
    private LocalDateTime updatedDate;
}
