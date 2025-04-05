package com.substring.foodie.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {
    private String cartItemId;
    private FoodItemDto foodItem;
    private int quantity;
}
