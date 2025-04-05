package com.substring.foodie.controller;

import com.substring.foodie.dto.AddItemToCartRequest;
import com.substring.foodie.dto.CartDto;
import com.substring.foodie.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDto> addItemToCart(@Valid @RequestBody AddItemToCartRequest request) {
        CartDto cart = cartService.addItemToCart(request);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId) {
        CartDto cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/user/{userId}/item/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartDto> removeItemFromCart(
            @PathVariable String userId,
            @PathVariable String cartItemId) {
        CartDto cart = cartService.removeItemFromCart(cartItemId, userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("card is cleared");
    }
}
