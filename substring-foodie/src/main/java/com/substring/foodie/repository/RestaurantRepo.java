package com.substring.foodie.repository;

import com.substring.foodie.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant, String> {
    List<Restaurant> findByNameContainingIgnoreCase(String keyword);
    Page<Restaurant> findByOpen(boolean flag, Pageable pageable);
    //TODO: get restaurant based on open timings
}
