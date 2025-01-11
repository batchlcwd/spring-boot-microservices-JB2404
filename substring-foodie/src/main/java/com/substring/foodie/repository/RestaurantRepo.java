package com.substring.foodie.repository;

import com.substring.foodie.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, String>
{

}
