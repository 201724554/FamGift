package com.example.FamGift.group.controller;

import com.example.FamGift.common.controller.BaseController;
import com.example.FamGift.group.dto.GroupAddDto;
import com.example.FamGift.group.dto.GroupDto;
import com.example.FamGift.group.facade.GroupFacade;
import com.example.FamGift.user_group.dto.UserGroupDto;
import com.example.FamGift.user_group.dto.UserGroupJoinDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GroupController implements BaseController {
    private final GroupFacade groupFacade;

    @GetMapping("/group")
    public ResponseEntity<List<GroupDto>> getGroup() {
        List<GroupDto> output = groupFacade.getGroupsByUser();
        return ResponseEntity.ok(output);
    }

    @PostMapping("/group")
    public ResponseEntity<String> addGroup(@RequestBody GroupAddDto dto) {
        groupFacade.addGroup(dto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/user/group")
    public ResponseEntity<String> addUserGroup(@RequestBody UserGroupJoinDto userGroupJoinDto) {
        groupFacade.joinGroup(userGroupJoinDto);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/group")
    public ResponseEntity<String> deleteGroup(@RequestBody GroupDto groupDto) {
        groupFacade.deleteGroup(groupDto);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/group/pswd")
    public ResponseEntity<String> updateGroupPswd(@RequestBody GroupDto groupDto) {
        groupFacade.updatePswd(groupDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/group/user/{groupId}")
    public ResponseEntity<List<UserGroupDto>> getUserGroup(@PathVariable Long groupId) {
        List<UserGroupDto> userGroups = groupFacade.findUserByGroup(groupId);
        return ResponseEntity.ok(userGroups);
    }

    @DeleteMapping("/userGroup")
    public ResponseEntity<String> deleteUserGroup(@RequestBody UserGroupDto userGroupDto) {
        groupFacade.deleteUserGroupId(userGroupDto);
        return ResponseEntity.ok(null);
    }
}
