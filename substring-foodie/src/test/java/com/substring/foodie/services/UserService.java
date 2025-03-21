package com.substring.foodie.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserService {

    @Autowired
    private com.substring.foodie.service.UserService userService;

    @Test
    public void testSaveUser(){
//        System.out.println("saving user");
//
//        //user
//
//        User user = new User();
//        user.setName("Ankush Kumar");
//        user.setEmail("ankus@gmail.com");
//        user.setAvailable(true);
//        user.setAddress("Test address, DELHI");
//        user.setPassword("abc");
//        user.setRole(Role.ADMIN);
//
//        // restaurant
//        Restaurant restaurant = new Restaurant();
//        restaurant.setId(UUID.randomUUID().toString());
//        restaurant.setName("KFC");
//        restaurant.setAddress("Lucknow");
//        restaurant.setOpen(true);
//
//
//        Restaurant restaurant1 = new Restaurant();
//        restaurant1.setId(UUID.randomUUID().toString());
//        restaurant1.setName("Haldiram");
//        restaurant1.setAddress("Lucknow");
//        restaurant1.setOpen(true);
//        restaurant1.setUser(user);
//        restaurant.setUser(user);
//        user.getRestaurants().add(restaurant);
//        user.getRestaurants().add(restaurant1);
//        User saved = userService.saveUser(user);
//        System.out.println(saved.getName());


    }

    @Test
    public void testUpdate(){

//        userService.testUserRole();
//
//        userService.updateUser();
//        System.out.println("user updated");
    }
}
