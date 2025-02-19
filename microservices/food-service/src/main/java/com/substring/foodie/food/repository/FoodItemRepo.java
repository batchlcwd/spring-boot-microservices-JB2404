package com.substring.foodie.food.repository;

import com.substring.foodie.food.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepo extends JpaRepository<FoodItem, String> {
}
