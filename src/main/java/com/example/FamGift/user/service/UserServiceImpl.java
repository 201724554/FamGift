package com.example.FamGift.user.service;

import com.example.FamGift.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.empty();
    }
}
