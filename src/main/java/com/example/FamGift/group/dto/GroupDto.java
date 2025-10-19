package com.example.FamGift.group.dto;


import com.example.FamGift.common.model.CommonYn;
import com.example.FamGift.group.model.Group;
import com.example.FamGift.user_group.model.UserGroup;
import lombok.Getter;

@Getter
public class GroupDto {
    private Long groupId;
    private String groupName;
    private String adminName;
    private String groupPassword;
    private String isAdmin;

    GroupDto() {}

    public GroupDto(Group group) {
        this.groupId = group.getId();
        this.groupName = group.getGroupName();
        this.adminName = group.getGroupAdmin().getName();
        this.groupPassword = group.getGroupPassword();
        this.isAdmin = CommonYn.Y.name();
    }

    public GroupDto(UserGroup userGroup) {
        this.groupId = userGroup.getGroup().getId();
        this.groupName = userGroup.getGroup().getGroupName();
    }
}
