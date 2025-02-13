package com.substring.foodie.restaurant.repository;

import com.substring.foodie.restaurant.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
   Optional<Restaurant> findByName(String name);

}
