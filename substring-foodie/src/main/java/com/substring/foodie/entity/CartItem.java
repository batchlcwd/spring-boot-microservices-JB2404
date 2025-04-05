package com.substring.foodie.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    @Id
    private String cartItemId;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private FoodItem foodItem;

    private int quantity;
}
