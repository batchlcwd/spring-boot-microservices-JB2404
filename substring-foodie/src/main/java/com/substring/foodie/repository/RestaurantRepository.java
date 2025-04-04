package com.substring.foodie.repository;

import com.substring.foodie.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    List<Restaurant> findByIsActive(boolean active);

    List<Restaurant> findByOpen(boolean open);

}
