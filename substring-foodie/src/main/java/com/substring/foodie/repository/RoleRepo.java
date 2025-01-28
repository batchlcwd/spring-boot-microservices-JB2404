package com.substring.foodie.repository;

import com.substring.foodie.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByName(String name);
}
