package com.substring.foodie.config.security;

import com.substring.foodie.entity.User;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepo;

    public CustomUserDetailService(UserRepository userRepo) {
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
