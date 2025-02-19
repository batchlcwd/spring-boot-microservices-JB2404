package com.substring.foodie.food.controller;

import com.substring.foodie.food.dto.FoodCategoryDTO;
import com.substring.foodie.food.dto.FoodItemDTO;
import com.substring.foodie.food.service.FoodCategoryService;
import com.substring.foodie.food.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food-items")
public class FoodItemController {



    /// /
    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public List<FoodItemDTO> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public FoodItemDTO getFoodItemById(@PathVariable String id) {
        return foodItemService.getFoodItemById(id);
    }

    @PostMapping
    public FoodItemDTO createFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        return foodItemService.createFoodItem(foodItemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodItem(@PathVariable String id) {
        foodItemService.deleteFoodItem(id);
    }

}

