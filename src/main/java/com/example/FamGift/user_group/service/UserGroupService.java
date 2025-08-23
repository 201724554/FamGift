package com.example.FamGift.user_group.service;

import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import com.example.FamGift.user_group.model.UserGroup;
import com.example.FamGift.user_group.repo.UserGroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void addUserGroup(UserGroup userGroup) {
        userGroupRepo.save(userGroup);
    }
}
