package com.substring.foodie.repository;

import com.substring.foodie.entity.Cart;
import com.substring.foodie.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCart(Cart cart);

}
