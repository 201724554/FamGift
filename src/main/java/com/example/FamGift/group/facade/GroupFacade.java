package com.example.FamGift.group.facade;

import com.example.FamGift.common.service.CommonService;
import com.example.FamGift.group.dto.GroupAddDto;
import com.example.FamGift.group.dto.GroupDto;
import com.example.FamGift.group.model.Group;
import com.example.FamGift.group.service.GroupService;
import com.example.FamGift.user.model.User;
import com.example.FamGift.user_group.model.UserGroup;
import com.example.FamGift.user_group.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupFacade {
    private final GroupService groupService;
    private final UserGroupService userGroupService;
    private final CommonService commonService;

    public List<GroupDto> getGroupsByUser() {
        User user = commonService.findUserByJwtToken();
        return groupService.findByUserGroup(user).stream().map(GroupDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void addGroup(GroupAddDto dto) {
        User groupOwner = commonService.findUserByJwtToken();
        String groupPassword = commonService.makeShortUuid();
        Group group = new Group(dto, groupOwner, groupPassword);
        groupService.addGroup(group);

        UserGroup userGroup = new UserGroup(groupOwner, group);
        userGroupService.addUserGroup(userGroup);
    }
}
