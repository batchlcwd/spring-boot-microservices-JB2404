package com.substring.foodie.food.repository;

import com.substring.foodie.food.entities.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepo extends JpaRepository<FoodCategory, String> {
}
