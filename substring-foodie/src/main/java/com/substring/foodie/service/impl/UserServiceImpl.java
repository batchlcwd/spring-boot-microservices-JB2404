package com.substring.foodie.service.impl;

import com.substring.foodie.entity.RoleEntity;
import com.substring.foodie.entity.User;
import com.substring.foodie.repository.UserRepo;
import com.substring.foodie.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    private UserRepo userRepo;


    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override

    public User saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        User savedEntity = userRepo.save(user);
        return savedEntity;
    }

    @Transactional
    public User updateUser(User user, String userId) {
        // get user
        // usko ek naya restaurant add karnuga

        User user1 = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        user1.setName(user.getName());
//        ..all fields
        User save = userRepo.save(user1);
        return save;
    }

    @Override
    public void testUserRole() {

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Vivek Ashok");
        user.setEmail("vivek@gmail.com");
        user.setAvailable(true);
        user.setAddress("Testing address for many to many");
        user.setPassword("abc");


        //
        RoleEntity entity1 = new RoleEntity();
        entity1.setName("ROLE_ADMIN");


        RoleEntity entity2 = new RoleEntity();
        entity2.setName("ROLE_GUEST");

        //link
        //user ki taraf se
        user.getRoleEntities().add(entity1);
        user.getRoleEntities().add(entity2);

        // entities
        entity1.getUsers().add(user);
        entity2.getUsers().add(user);

        userRepo.save(user);

        System.out.println("user saved:");


    }
}
