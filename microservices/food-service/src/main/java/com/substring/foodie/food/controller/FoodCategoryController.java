package com.substring.foodie.food.controller;


import com.substring.foodie.food.dto.FoodCategoryDTO;
import com.substring.foodie.food.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/food-categories")
public class FoodCategoryController {


    @Autowired
    private FoodCategoryService foodCategoryService;


    @GetMapping
    public List<FoodCategoryDTO> getAllFoodCategories() {
        return foodCategoryService.getAllFoodCategories();
    }


    @GetMapping("/{id}")
    public FoodCategoryDTO getFoodCategoryById(@PathVariable String id) {
        return foodCategoryService.getFoodCategoryById(id);
    }

    @PostMapping
    public FoodCategoryDTO createFoodCategory(@RequestBody FoodCategoryDTO foodCategoryDTO) {
        return foodCategoryService.createFoodCategory(foodCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodCategory(@PathVariable String id) {
        foodCategoryService.deleteFoodCategory(id);
    }
}
