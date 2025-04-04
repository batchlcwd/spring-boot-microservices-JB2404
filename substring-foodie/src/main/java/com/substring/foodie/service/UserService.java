package com.substring.foodie.service;

import com.substring.foodie.dto.UserDto;
import com.substring.foodie.entity.User;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto user);
    UserDto updateUser(UserDto user, String userId);

    void deleteUser(String userId);
    UserDto getUser(String userId);
    UserDto getUserByEmail(String email);
    List<UserDto> getUsers();
    List<UserDto> searchUserName(String keyword);


}
