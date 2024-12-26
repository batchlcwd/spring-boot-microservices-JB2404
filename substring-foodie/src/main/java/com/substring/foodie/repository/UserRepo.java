package com.substring.foodie.repository;

import com.substring.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String>
{
    // custom query methods
}