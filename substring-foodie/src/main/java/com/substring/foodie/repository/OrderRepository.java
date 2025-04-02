package com.substring.foodie.repository;

import com.substring.foodie.entity.Order;
import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByRestaurant(Restaurant restaurant);

    List<Order> findByUser(User user);

    List<Order> findByDeliveryBoy(User user);

}
