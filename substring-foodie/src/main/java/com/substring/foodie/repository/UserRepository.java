package com.substring.foodie.repository;

import com.substring.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    //email: return the optional of user
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


}
