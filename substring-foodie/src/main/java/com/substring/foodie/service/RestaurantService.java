package com.substring.foodie.service;

import com.substring.foodie.dto.OrderDto;
import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.entity.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {
    RestaurantDto addRestaurant(RestaurantDto restaurant);

    RestaurantDto updateRestaurant(RestaurantDto restaurant, String restaurantId);

    void deleteRestaurant(String restaurantId);

    RestaurantDto getRestaurant(String restaurantId);

    Page<RestaurantDto> getRestaurants(Pageable pageable);

    List<RestaurantDto> getByOwner(String ownerId);

    List<RestaurantDto> searchByName(String nameKeyword);

    List<RestaurantDto> searchByAddress(String address);

    List<RestaurantDto> getByIsActive(Boolean isActive);

    List<RestaurantDto> getByOpen(Boolean isOpen);




}
