package com.example.FamGift.user.service;

import com.example.FamGift.user.model.User;
import com.example.FamGift.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByUserId(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return  userRepository.findUserByName(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
