package com.substring.foodie.repository;

import com.substring.foodie.entity.Order;
import com.substring.foodie.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);

}
