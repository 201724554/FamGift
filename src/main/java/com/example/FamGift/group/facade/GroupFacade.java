package com.example.FamGift.group.facade;

import com.example.FamGift.common.exception.CustomException;
import com.example.FamGift.common.exception.ErrorMessage;
import com.example.FamGift.common.service.CommonService;
import com.example.FamGift.gifticon.service.GifticonService;
import com.example.FamGift.group.dto.GroupAddDto;
import com.example.FamGift.group.dto.GroupDto;
import com.example.FamGift.group.model.Group;
import com.example.FamGift.group.service.GroupService;
import com.example.FamGift.user.model.User;
import com.example.FamGift.user_group.dto.UserGroupDto;
import com.example.FamGift.user_group.dto.UserGroupJoinDto;
import com.example.FamGift.user_group.model.UserGroup;
import com.example.FamGift.user_group.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.FamGift.common.exception.ErrorMessage.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroupFacade {
    private final GroupService groupService;
    private final UserGroupService userGroupService;
    private final GifticonService gifticonService;
    private final CommonService commonService;

    public List<GroupDto> getGroupsByUser() {
        User user = commonService.findUserByJwtToken();
        return groupService.findByUserGroup(user).stream().map(GroupDto::new).collect(Collectors.toList());
    }

    public void addGroup(GroupAddDto dto) {
        User groupOwner = commonService.findUserByJwtToken();
        String groupPassword = commonService.makeShortUuid();
        Group group = new Group(dto, groupOwner, groupPassword);
        addGroup_Transaction(group, groupOwner);
    }

    @Transactional
    public void addGroup_Transaction(Group group, User groupOwner) {
        groupService.addGroup(group);
        UserGroup userGroup = new UserGroup(groupOwner, group);
        userGroupService.addUserGroup(userGroup);
    }

    public void joinGroup(UserGroupJoinDto userGroupJoinDto) {
        User user = commonService.findUserByJwtToken();
        //select Group
        Group group = groupService.findGroupByGroupPassword(userGroupJoinDto.getGroupPswd()).orElseThrow(() -> new CustomException(ELEM_NOT_FOUND));
        //compare pswd
        if(group.getGroupAdmin().getId().equals(user.getId())) {
            throw new CustomException(GROUP_MANAGED);
        }
        //add UserGroup
        userGroupService.addUserGroup(new UserGroup(user, group));
    }

    public void deleteGroup(GroupDto groupDto) {
        User user = commonService.findUserByJwtToken();
        Group group = groupService.findGroupById(groupDto.getGroupId()).orElseThrow(NoSuchElementException::new);

        if(!user.isEqualTo(group.getGroupAdmin())) {
            throw new CustomException(NOT_ALLOWED);
        }

        deleteGroupTransaction(groupDto);
    }

    @Transactional
    private void deleteGroupTransaction(GroupDto groupDto) {
        //delete user-group
        userGroupService.deleteByGroupId(groupDto.getGroupId());
        //set gifticon.group = null
        gifticonService.updateGifticonGroupToNullByGroupId(groupDto.getGroupId());
        //delete group
        groupService.deleteGroup(groupDto.getGroupId());
    }

    public void updatePswd(GroupDto groupDto) {
        User user = commonService.findUserByJwtToken();
        Group group = groupService.findGroupById(groupDto.getGroupId()).orElseThrow(NoSuchElementException::new);

        if(!user.isEqualTo(group.getGroupAdmin())) {
            throw new CustomException(NOT_ALLOWED);
        }

        String newPswd = commonService.makeShortUuid();
        groupService.updateGroupPswd(group, newPswd);
    }

    public List<UserGroupDto> findUserByGroup(Long groupId) {
        Group group = groupService.findGroupById(groupId).orElseThrow(NoSuchElementException::new);
        return userGroupService.findByGroup(group)
                .stream().map(UserGroupDto::new).collect(Collectors.toList());
    }

    public void deleteUserGroupId(UserGroupDto userGroupDto) {
        //check groupAdmin = requester
        Group targetGroup = groupService.findGroupById(userGroupDto.getGroupId()).orElseThrow(() -> new CustomException(ELEM_NOT_FOUND));
        User groupAdmin = targetGroup.getGroupAdmin();
        User user = commonService.findUserByJwtToken();

        if(!user.isEqualTo(groupAdmin)) {
            throw new CustomException(USER_ADMIN_NOT_MATCH);
        }
        //delete
        userGroupService.deleteById(userGroupDto.getUserGroupId());
    }
}
