package com.substring.foodie.restaurant.controller;

import com.substring.foodie.restaurant.service.RestaurantService;
import com.substring.foodie.restaurant.dto.RestaurantDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;

    }


    // Create a new restaurant
    @PostMapping
    public ResponseEntity<RestaurantDto> save(@RequestBody RestaurantDto dto) {
        RestaurantDto savedRestaurant = restaurantService.save(dto);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    // Update an existing restaurant
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> update(@PathVariable String id, @RequestBody RestaurantDto dto) {
        RestaurantDto updatedRestaurant = restaurantService.update(id, dto);
        return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
    }

    // Get restaurant by ID
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getById(@PathVariable String id) {
        RestaurantDto dto = restaurantService.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Find restaurant by name
    @GetMapping("/name/{name}")
    public ResponseEntity<RestaurantDto> findByName(@PathVariable String name) {
        RestaurantDto dto = restaurantService.findByName(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Delete restaurant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        restaurantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAll() {
        List<RestaurantDto> restaurants = restaurantService.getAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
}
