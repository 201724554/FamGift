package com.example.FamGift.group.repo;

import com.example.FamGift.group.dto.GroupDto;
import com.example.FamGift.group.model.Group;
import com.example.FamGift.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {
    List<Group> findByGroupAdmin(User admin);
    Optional<Group> findById(Long id);

    @Query("SELECT g " +
            "FROM UserGroup ug " +
            "JOIN ug.group g " +
            "WHERE ug.user = :user")
    List<Group> findByUserGroup(@Param(value = "user") User user);
}
