package com.jpa.example.service;

import com.jpa.example.entity.Laptop;
import com.jpa.example.entity.User;
import com.jpa.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void testService(){



    }
}
