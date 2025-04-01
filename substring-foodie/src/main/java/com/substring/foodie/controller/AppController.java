package com.substring.foodie.controller;

import com.substring.foodie.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/app")
public class AppController {

    private UserRepository userRepository;

    public AppController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String test() {
        return "App is running " + userRepository.findAll().size() + " users";
    }
}
