package com.substring.foodie.service;

import com.substring.foodie.entity.User;

public interface UserService {

    User saveUser(User user);
    public User updateUser(User user, String userId);

    public void testUserRole();
}
