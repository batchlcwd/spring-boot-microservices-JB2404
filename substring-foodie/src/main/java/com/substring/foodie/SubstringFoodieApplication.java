package com.substring.foodie;

import com.substring.foodie.entity.User;
import com.substring.foodie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubstringFoodieApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SubstringFoodieApplication.class, args);
    }

    @Autowired
    private UserRepository
            userRepository;


    @Override
    public void run(String... args) throws Exception {


        User user = new User();
        user.setId("user123");
        user.setName("Pritam Ray");
        user.setPassword("abc123");
        user.setEmail("pritam@gmail.com");

        user.setEnabled(true);
        user.setAvailable(true);

        userRepository.findById("user123").ifPresentOrElse(user1 -> {
            System.out.println("user is there");
        }, () -> {
            userRepository.save(user);
        });

    }
}
