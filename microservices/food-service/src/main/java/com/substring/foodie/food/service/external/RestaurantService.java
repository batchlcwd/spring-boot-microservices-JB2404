package com.substring.foodie.food.service.external;

import com.substring.foodie.food.config.AppConstants;
import com.substring.foodie.food.dto.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "restaurant-service", url = "http://restaurant-service")
@FeignClient(name = AppConstants.RESTAURANT_SERVICE_NAME)
public interface RestaurantService {


    // get restaurants with given id:

    @GetMapping("/api/v1/restaurants/{id}")
    RestaurantDto getById(@PathVariable("id") String restaurantId);

    // get all

    @GetMapping("/api/v1/restaurants")
    List<RestaurantDto> getAll();

    // post api

    @PostMapping("/api/v1/restaurants")
    RestaurantDto create(
            @RequestBody RestaurantDto dto
    );

    // delete

    @DeleteMapping("/api/v1/restaurants/{id}")
    void delete(@PathVariable("id") String restaurantId);

    // update


}
