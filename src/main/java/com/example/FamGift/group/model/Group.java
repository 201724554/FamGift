package com.example.FamGift.group.model;

import com.example.FamGift.common.model.CommonEntity;
import com.example.FamGift.group.dto.GroupAddDto;
import com.example.FamGift.user.model.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_GROUP", uniqueConstraints = @UniqueConstraint(name = "UNIQUE_ADMIN_NAME", columnNames = {"GROUP_ADMIN_ID", "GROUP_NAME"}))
@Getter
public class Group extends CommonEntity {
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

    public Group(User groupAdmin, String groupPassword, String groupName) {
        this.groupAdmin = groupAdmin;
        this.groupPassword = groupPassword;
        this.groupName = groupName;
    }

    public void updateGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void updateGroupPassword(String groupPassword) {
        this.groupPassword = groupPassword;
    }

    public Group() {}

    public Group(GroupAddDto dto, User groupAdmin, String groupPassword) {
        this.groupName = dto.getGroupName();
        this.groupPassword = groupPassword;
        this.groupAdmin = groupAdmin;
    }
}
