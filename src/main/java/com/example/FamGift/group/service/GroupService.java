package com.example.FamGift.group.service;

import com.example.FamGift.group.model.Group;
import com.example.FamGift.group.repo.GroupRepo;
import com.example.FamGift.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepo groupRepo;

    public List<Group> findByGroupAdmin(User user) {
        return groupRepo.findByGroupAdmin(user);
    }

    public List<Group> findByUserGroup(User user) {
        return groupRepo.findByUserGroup(user);
    }

    @Transactional
    public void addGroup(Group group) {
        groupRepo.save(group);
    }

    public Optional<Group> findGroupById(Long id) {
        return groupRepo.findById(id);
    }

    public Optional<Group> findGroupByGroupPassword(String groupPassword) {
        return groupRepo.findByGroupPassword(groupPassword);
    }

    public void deleteGroup(Long groupId) {
        groupRepo.deleteById(groupId);
    }

    @Transactional
    public void updateGroupPswd(Group group, String newPswd) {
        group.updateGroupPassword(newPswd);
    }
}
