package com.substring.foodie.service;

import com.substring.foodie.dto.AddItemToCartRequest;
import com.substring.foodie.dto.CartDto;
import com.substring.foodie.entity.CartItem;
import java.util.List;

public interface CartService {

    CartDto addItemToCart(AddItemToCartRequest addItemToCartRequest);

    CartDto getCart(String userId);

    CartDto removeItemFromCart(String cartItemId, String userId);

    List<CartItem> getCartItems(String userId);

    void clearCart(String userId);
}
