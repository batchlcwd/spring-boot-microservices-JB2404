package com.substring.foodie.dto;

public record LoginRequest(
        String email,
        String password
) {
}
