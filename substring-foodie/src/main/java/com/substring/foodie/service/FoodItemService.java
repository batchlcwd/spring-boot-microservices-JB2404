package com.substring.foodie.service;

import com.substring.foodie.dto.FoodItemDto;
import java.util.List;

public interface FoodItemService {
    FoodItemDto addFood(FoodItemDto foodItemDto);

    FoodItemDto updateFood(Long foodId, FoodItemDto foodItemDto);

    void deleteFood(Long foodId);

    List<FoodItemDto> getAllFoods();

    List<FoodItemDto> getFoodsByRestaurant(String restaurantId);

    FoodItemDto getFoodById(Long foodId);
}
