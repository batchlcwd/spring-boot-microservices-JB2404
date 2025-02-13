package com.substring.foodie.restaurant.service;

import com.substring.foodie.restaurant.dto.RestaurantDto;
import com.substring.foodie.restaurant.entities.Restaurant;
import com.substring.foodie.restaurant.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService {


    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Save a new restaurant
    public RestaurantDto save(RestaurantDto dto) {
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(dto, restaurant);
        //we generating random id for restaurant
        restaurant.setId(UUID.randomUUID().toString());
        restaurant = restaurantRepository.save(restaurant);
        BeanUtils.copyProperties(restaurant, dto);
        return dto;
    }

    // Update an existing restaurant
    public RestaurantDto update(String id, RestaurantDto dto) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            BeanUtils.copyProperties(dto, restaurant);
            restaurant = restaurantRepository.save(restaurant);
            BeanUtils.copyProperties(restaurant, dto);
            return dto;
        }
        throw new RuntimeException("Restaurant not found with id: " + id);
    }

    // Get restaurant by ID
    public RestaurantDto getById(String id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            RestaurantDto dto = new RestaurantDto();
            BeanUtils.copyProperties(optionalRestaurant.get(), dto);
            return dto;
        }
        throw new RuntimeException("Restaurant not found with id: " + id);
    }

    @Transactional
    // Find restaurant by name
    public RestaurantDto findByName(String name) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findByName(name);
        if (optionalRestaurant.isPresent()) {
            RestaurantDto dto = new RestaurantDto();
            BeanUtils.copyProperties(optionalRestaurant.get(), dto);
            return dto;
        }
        throw new RuntimeException("Restaurant not found with name: " + name);
    }

    // Delete restaurant by ID
    public void delete(String id) {
        restaurantRepository.deleteById(id);
    }

    // Get all restaurants
    public List<RestaurantDto> getAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDto> dtos = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            RestaurantDto dto = new RestaurantDto();
            BeanUtils.copyProperties(restaurant, dto);
            dtos.add(dto);
        }
        return dtos;
    }
}
