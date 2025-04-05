package com.substring.foodie.service.impl;

import com.substring.foodie.dto.FoodItemDto;
import com.substring.foodie.entity.FoodItem;
import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.FoodItemRepository;
import com.substring.foodie.repository.RestaurantRepository;
import com.substring.foodie.service.FoodItemService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public FoodItemDto addFood(FoodItemDto foodItemDto) {

        Restaurant restaurant = restaurantRepository.findById(foodItemDto.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemDto.getName());
        foodItem.setDescription(foodItemDto.getDescription());
        foodItem.setPrice(foodItemDto.getPrice());
        foodItem.setAvailable(foodItemDto.isAvailable());
        foodItem.setImageUrl(foodItemDto.getImageUrl());
        foodItem.setFoodType(foodItemDto.getFoodType());
        foodItem.setCreatedDate(LocalDateTime.now());
        foodItem.setDiscountAmount(foodItemDto.getDiscountAmount());
        foodItem.setRestaurant(restaurant);
        FoodItem savedFood = foodItemRepository.save(foodItem);
        return convertToDto(savedFood);
    }

    @Override
    public FoodItemDto updateFood(Long foodId, FoodItemDto foodItemDto) {

        Restaurant restaurant = restaurantRepository.findById(foodItemDto.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        FoodItem existingFood = foodItemRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));
        existingFood.setName(foodItemDto.getName());
        existingFood.setDescription(foodItemDto.getDescription());
        existingFood.setPrice(foodItemDto.getPrice());
        existingFood.setAvailable(foodItemDto.isAvailable());
        existingFood.setImageUrl(foodItemDto.getImageUrl());
        existingFood.setFoodType(foodItemDto.getFoodType());
        existingFood.setDiscountAmount(foodItemDto.getDiscountAmount());
        existingFood.setRestaurant(restaurant);
        FoodItem updatedFood = foodItemRepository.save(existingFood);
        return convertToDto(updatedFood);
    }

    @Override
    public void deleteFood(Long foodId) {
        foodItemRepository.deleteById(foodId);
    }

    @Override
    public List<FoodItemDto> getAllFoods() {
        return foodItemRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodItemDto> getFoodsByRestaurant(String restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        return foodItemRepository.findByRestaurant(restaurant).stream()
                .filter(food -> food.isAvailable())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FoodItemDto getFoodById(Long foodId) {
        FoodItem foodItem = foodItemRepository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found"));
        return convertToDto(foodItem);
    }

    @Autowired
    private ModelMapper mapper;

    private FoodItemDto convertToDto(FoodItem foodItem) {
        return mapper.map(foodItem, FoodItemDto.class);
    }
}
