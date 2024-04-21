package com.example.FamGift.user.service;

import com.example.FamGift.common.service.BaseService;
import com.example.FamGift.user.model.User;

import java.util.Optional;

public interface UserService extends BaseService {
    Optional<User> findUserByUsername(String username);
}
