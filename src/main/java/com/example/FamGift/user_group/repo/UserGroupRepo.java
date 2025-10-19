package com.example.FamGift.user_group.repo;

import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import com.example.FamGift.user_group.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepo extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findByUser(User user);
    void deleteByGroupId(Long groupId);
    void deleteById(Long id);
    List<UserGroup> findByGroup(Group group);
}
