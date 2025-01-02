package com.substring.foodie.controller;

import com.substring.foodie.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Controller
@RestController
@RequestMapping("/users")
public class UserController {


    @RequestMapping("/")
    //@ResponseBody
    public String testing() {
        System.out.println("user getting");
        String template = "this is template";
        return template;
    }

    @RequestMapping("/player-list")
    public List<String> players() {
        List<String> list = new ArrayList<>();
        list.add("Sachin");
        list.add("MS Dhoni");
        list.add("Virat");
        list.add("Rahul");
        list.add("Dravid");
        list.add("Rishabh Pant");
        list.add("Rohit");
        return list;

    }

    //
    @RequestMapping("/get-user")
    public User getUser() {

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Sachin");
        user.setEmail("sachin@gmail.com");
        user.setPassword("123456");
        user.setAddress("5/2354 Section 12 noida india");
        user.setAvailable(true);

        return user;
    }


}
