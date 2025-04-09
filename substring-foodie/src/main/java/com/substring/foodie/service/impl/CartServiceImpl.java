package com.substring.foodie.service.impl;

import com.substring.foodie.dto.AddItemToCartRequest;
import com.substring.foodie.dto.CartDto;
import com.substring.foodie.entity.Cart;
import com.substring.foodie.entity.CartItem;
import com.substring.foodie.entity.FoodItem;
import com.substring.foodie.entity.User;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.CartRepository;
import com.substring.foodie.repository.FoodItemRepository;
import com.substring.foodie.repository.UserRepository;
import com.substring.foodie.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired

    private UserRepository userRepository;

    @Override
    public CartDto addItemToCart(AddItemToCartRequest request) {
        String userId = request.getUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        // getting or building a cart
        Cart cart = cartRepository.findByUser(user)
                .orElse(Cart.builder()
                        .cartId(UUID.randomUUID().toString())
                        .items(new ArrayList<>())
                        .user(user)
                        .build());

        FoodItem foodItem = foodItemRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found"));

        // valid ==20
        if (!foodItem.isAvailable()) {
            throw new ResourceNotFoundException("Food item is not available");
        }

        boolean isFromSameRestaurant = true;
        for (CartItem item : cart.getItems()) {
            if (!item.getFoodItem().getRestaurant().getId().equals(foodItem.getRestaurant().getId())) {
                isFromSameRestaurant = false;
                break;
            }
        }

        if (!isFromSameRestaurant) {
            throw new ResourceNotFoundException(
                    "Food item is not from the same restaurant, please clear the card and then add the item");
        }

        // Check if item already exists in cart
        boolean itemExists = false;
        for (CartItem item : cart.getItems()) {
            if (item.getFoodItem().getId().equals(request.getProductId())) {
                System.out.println("Matched");
                // If item exists, increase quantity
                item.setQuantity(item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        // If item doesn't exist, create new cart item
        if (!itemExists) {
            CartItem cartItem = CartItem.builder()
                    .cartItemId(UUID.randomUUID().toString())
                    .cart(cart)
                    .foodItem(foodItem)
                    .quantity(1)
                    .build();
            cart.getItems().add(cartItem);
        }

        cart = cartRepository.save(cart);
        return convertToDto(cart);
    }

    @Override
    public CartDto getCart(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return convertToDto(cart);
    }

    @Override
    public CartDto removeItemFromCart(String cartItemId, String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cart.getItems().removeIf(item -> item.getCartItemId().equals(cartItemId));
        cart = cartRepository.save(cart);
        return convertToDto(cart);
    }

    @Override
    public void clearCart(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().clear();
        cart = cartRepository.save(cart);

    }

    @Autowired
    private ModelMapper mapper;

    private CartDto convertToDto(Cart cart) {
        return mapper.map(cart, CartDto.class);
    }

    @Override
    public List<CartItem> getCartItems(String userId) {
        throw new UnsupportedOperationException("Unimplemented method 'getCartItems'");
    }

}
