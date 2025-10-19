package com.example.FamGift.user_group.dto;

import com.example.FamGift.user_group.model.UserGroup;
import lombok.Getter;

@Getter
public class UserGroupDto {
    private Long userGroupId;
    private Long userId;
    private String userName;
    private Long groupId;

    public UserGroupDto() {}
    public UserGroupDto(UserGroup userGroup) {
        this.userGroupId = userGroup.getId();
        this.userId = userGroup.getUser().getId();
        this.userName = userGroup.getUser().getName();
        this.groupId = userGroup.getGroup().getId();
    }
}
