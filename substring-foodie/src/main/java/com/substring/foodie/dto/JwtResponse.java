package com.substring.foodie.dto;

import com.substring.foodie.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse {
    private String token;

    private UserDto user;

}
