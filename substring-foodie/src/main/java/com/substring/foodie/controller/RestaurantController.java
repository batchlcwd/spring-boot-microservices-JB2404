package com.substring.foodie.controller;

import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.service.RestaurantService;
import com.substring.foodie.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final UserService userService;
    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }


    //create restaurant:
    

    @GetMapping
    public ResponseEntity<Page<RestaurantDto>> restaurants(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "desc") String sortDir
    ) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<RestaurantDto> restaurants = restaurantService.getRestaurants(pageable);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurant( @PathVariable String id) {
           return new ResponseEntity<>(restaurantService.getRestaurant(id), HttpStatus.OK);
    }
}
