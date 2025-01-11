package com.substring.foodie.service;

import com.substring.foodie.dto.RestaurantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {

    // add logic

    RestaurantDto add(RestaurantDto restaurantDto);

    RestaurantDto update(RestaurantDto restaurantDto, String id);

    void delete(String delete);

    //get single
    RestaurantDto get(String id);

    // get all
    Page<RestaurantDto> getAll(Pageable pageable);

    List<RestaurantDto> searchByName(String keyword);

    //extra service add these services
    Page<RestaurantDto> getOpenRestaurants(Pageable pageable);



}
