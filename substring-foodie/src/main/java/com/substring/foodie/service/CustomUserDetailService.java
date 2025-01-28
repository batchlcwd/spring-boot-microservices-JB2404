package com.substring.foodie.service;

import com.substring.foodie.entity.User;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.UserRepo;
import com.substring.foodie.security.CustomUserDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ye wala method call hoga
        User user = userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        return customUserDetail;
    }
}
