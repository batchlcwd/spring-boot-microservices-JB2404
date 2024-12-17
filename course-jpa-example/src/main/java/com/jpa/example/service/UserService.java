package com.jpa.example.service;

import com.jpa.example.entity.User;
import com.jpa.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //save user:
    public User save(User user) {
        return userRepository.save(user);
    }

    public User get(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
    }
}
