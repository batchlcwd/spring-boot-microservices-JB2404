package com.substring.foodie.controller;

import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.service.RestaurantService;
import com.substring.foodie.service.UserService;
import com.substring.foodie.utils.Helper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final UserService userService;
    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    // create restaurant:

    @Operation(
            summary = "Get all restaurants",
            description = "Retrieve a restaurant by its ID. The ID must be a valid UUID.",
            tags = "Restaurant Get"
    )
    @GetMapping
    public ResponseEntity<Page<RestaurantDto>> restaurants(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<RestaurantDto> restaurants = restaurantService.getRestaurants(pageable);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);

    }

    @Operation(
            summary = "Get restaurant by ID",
            description = "Retrieve a restaurant by its ID. The ID must be a valid UUID.",
            tags = "Restaurant Get"
    )
    @ApiResponse(responseCode = "200", description = "Restaurant found")
    @ApiResponse(responseCode = "404", description = "Restaurant not found")
    @ApiResponse(responseCode = "400", description = "Invalid ID format")

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable String id) {
        return new ResponseEntity<>(restaurantService.getRestaurant(id), HttpStatus.OK);
    }

    // Get restaurants by owner
    @Operation(
            summary = "Get restaurants by owner ID",
            tags = "Restaurant Get"
    )
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByOwner(@PathVariable String ownerId) {
        List<RestaurantDto> restaurants = restaurantService.getByOwner(ownerId);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    // Get restaurants by active and open status
    @Operation(
            summary = "Get restaurants by status",
            tags = "Restaurant Get"
    )
    @GetMapping("/status")
    public ResponseEntity<List<RestaurantDto>> getByIsActiveAndOpen(
            @RequestParam(value = "isActive") Boolean isActive,
            @RequestParam(value = "isOpen") Boolean isOpen) {
        List<RestaurantDto> restaurants = restaurantService.getByIsActiveAndOpen(isActive, isOpen);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    // Search restaurants by name
    @Operation(
            summary = "search restaurants by name",
            tags = "Restaurant Get"
    )
    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDto>> searchByName(@RequestParam("name") String nameKeyword) {
        List<RestaurantDto> restaurants = restaurantService.searchByName(nameKeyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    // Search restaurants by address
    @Operation(
            summary = "Search by address",
            tags = "Restaurant Get"
    )
    @GetMapping("/search/address")
    public ResponseEntity<List<RestaurantDto>> searchByAddress(@RequestParam("address") String address) {
        List<RestaurantDto> restaurants = restaurantService.searchByAddress(address);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    // Create restaurant
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantDto> addRestaurant(@Valid @RequestBody RestaurantDto restaurantDto) {

        restaurantDto.setId(Helper.generateRandomId());
        RestaurantDto createdRestaurant = restaurantService.addRestaurant(restaurantDto);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    // Update restaurant
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantDto> updateRestaurant(@Valid @RequestBody RestaurantDto restaurantDto,
                                                          @PathVariable String id) {
        RestaurantDto updatedRestaurant = restaurantService.updateRestaurant(restaurantDto, id);
        return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
    }

    // Delete restaurant
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
