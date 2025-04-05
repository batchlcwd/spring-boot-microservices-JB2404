package com.substring.foodie.controller;

import com.substring.foodie.dto.FoodItemDto;
import com.substring.foodie.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foods")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RESTAURANT')")
    public ResponseEntity<FoodItemDto> addFood(@RequestBody FoodItemDto foodItemDto) {
        return new ResponseEntity<>(foodItemService.addFood(foodItemDto), HttpStatus.CREATED);
    }

    @PutMapping("/{foodId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RESTAURANT')")
    public ResponseEntity<FoodItemDto> updateFood(@PathVariable Long foodId, @RequestBody FoodItemDto foodItemDto) {
        return ResponseEntity.ok(foodItemService.updateFood(foodId, foodItemDto));
    }

    @DeleteMapping("/{foodId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RESTAURANT')")
    public ResponseEntity<Void> deleteFood(@PathVariable Long foodId) {
        foodItemService.deleteFood(foodId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FoodItemDto>> getAllFoods() {
        return ResponseEntity.ok(foodItemService.getAllFoods());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FoodItemDto>> getFoodsByRestaurant(@PathVariable String restaurantId) {
        return ResponseEntity.ok(foodItemService.getFoodsByRestaurant(restaurantId));
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodItemDto> getFoodById(@PathVariable Long foodId) {
        return ResponseEntity.ok(foodItemService.getFoodById(foodId));
    }
}
