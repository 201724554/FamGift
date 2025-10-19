package com.example.FamGift.user_group.service;

import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import com.example.FamGift.user_group.model.UserGroup;
import com.example.FamGift.user_group.repo.UserGroupRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserGroupService {
    private final UserGroupRepo userGroupRepo;

    public List<UserGroup> findByUser(User user) {
        return userGroupRepo.findByUser(user);
    }

    public List<Group> findGroupByUser(User user) {
        return userGroupRepo.findByUser(user).stream().map(UserGroup::getGroup).collect(Collectors.toList());
    }

    @Transactional
    public void addUserGroup(UserGroup userGroup) {
        userGroupRepo.save(userGroup);
    }

    public void deleteByGroupId(Long groupId) {
        userGroupRepo.deleteByGroupId(groupId);
    }

    @Transactional
    public void deleteById(Long id) {
        userGroupRepo.deleteById(id);
    }

    public List<UserGroup> findByGroup(Group group) {
        return userGroupRepo.findByGroup(group);
    }
}
