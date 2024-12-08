package com.example.FamGift.security;

import com.example.FamGift.user.model.User;
import com.example.FamGift.user.service.UserService;

import java.util.Optional;

public class PrincipalService implements UserService {
    @Override
    public Optional<User> findUserByUserId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public User saveUser(User user) {
        return null;
    }
}
