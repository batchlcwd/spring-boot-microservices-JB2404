package com.substring.foodie.entity;

import com.substring.foodie.entity.enums.FoodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FoodItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int price;
    private boolean isAvailable;
    @Enumerated(EnumType.STRING)
    private FoodType foodType = FoodType.VEG;
    private String imageUrl;
    private LocalDateTime createdDate;
    private int discountAmount;
    @ManyToOne
    private Restaurant restaurant;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    public int actualPrice() {
        return price - discountAmount;
    }

    public int getDiscountPrecentage() {
        return (discountAmount / price) * 100;
    }


}
