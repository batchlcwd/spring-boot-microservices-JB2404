package com.substring.foodie.food.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "food_service_food_item")
@Getter
@Setter
public class FoodItem {
    @Id
    private String id;

    private String title;

    private String description;

    private int quantity;

    private boolean outOfStock = true;

    @Enumerated(EnumType.STRING)
    private FoodType foodType = FoodType.VEG;

    @ManyToOne
    private  FoodCategory foodCategory;

//    store the restaurant information inside food item

    @Column(nullable = false)
    private  String restaurantId;



}
