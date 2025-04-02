package com.substring.foodie.service;


import com.substring.foodie.dto.AddItemToCartRequest;
import com.substring.foodie.dto.CartDto;

public interface CartService {

    CartDto addItemToCart(AddItemToCartRequest addItemToCartRequest);

    CartDto getCart(String userId);

    CartDto removeItemFromCart(String cartItemId, String userId);

    CartDto clearCart(String userId);



}
