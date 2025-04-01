package com.substring.foodie.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }




//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User.builder().username("user").password("{noop}user").build();
//
//        UserDetails admin = User.builder().username("admin").password("{noop}admin").build();
//
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1, admin);
//
//
//        return inMemoryUserDetailsManager;
//    }


}
