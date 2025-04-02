package com.substring.foodie.repository;

import com.substring.foodie.entity.FoodItem;
import com.substring.foodie.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByRestaurant(Restaurant restaurant);
}
