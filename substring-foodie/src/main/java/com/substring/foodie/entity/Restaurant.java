package com.substring.foodie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foodie_restaurant")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {

    @Id
    private String id;

    private String name;

    @Lob
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Boolean open = true;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    private boolean isActive = true;
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodItem> foodItems = new ArrayList<>();

    private String bannerImageUrl;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }


}
