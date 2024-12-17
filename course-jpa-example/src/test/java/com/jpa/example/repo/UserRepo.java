package com.jpa.example.repo;

import com.jpa.example.entity.User;
import com.jpa.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepo {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void repoTest1() {

        System.out.println("Testing repo");

        int i = userRepository.countByName("Azaz Khan");
        System.out.println(i);

        System.out.println(userRepository.existsByEmail("Vivek@gmail.com"));

        List<User> llist = userRepository.findByNameContaining("Durges");
        llist.forEach(user->{
            System.out.println(user.getName());
        });


//        Optional<User> userOptional = userRepository.findByEmailOrderByNameAsc("vivek@gmail.com");
//
//        User user = userOptional.orElseThrow(() -> new RuntimeException("user not found"));
//        System.out.println(user.getUserId());
//        System.out.println(user.getName());
//        System.out.println(user.getEmail());


//        userOptional.ifPresentOrElse(user -> {
//            System.out.println(user.getUserId());
//            System.out.println(user.getName());
//            System.out.println(user.getEmail());
//        },() -> {
//            System.out.println("User not found !!");
//        });

    }

    @Test
    public void repoTest2(){

        List<User> india = userRepository.getUserByCountry("America");
        System.out.println(india.size());
    }

}
