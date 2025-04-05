package com.substring.foodie.repository;

import com.substring.foodie.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByRestaurantId(String restaurantId);

    List<Order> findByUserId(String userId);

    List<Order> findByDeliveryBoyId(String deliveryBoyId);
}
