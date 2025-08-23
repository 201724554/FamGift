package com.example.FamGift.user_group.model;

import com.example.FamGift.common.model.CommonEntity;
import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER_GROUP", uniqueConstraints = @UniqueConstraint(name = "UNIQUE_USER_GROUP", columnNames = {"USER_ID", "GROUP_ID"}))
@Getter
public class UserGroup extends CommonEntity {
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

    public UserGroup() {}
    public UserGroup(User user, Group group) {
        this.user = user;
        this.group = group;
    }
}
