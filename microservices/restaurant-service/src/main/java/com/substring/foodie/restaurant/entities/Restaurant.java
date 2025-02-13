package com.substring.foodie.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    @ElementCollection
    private List<String> pictures = new ArrayList<>();

    // priority 1 open true ki false
    private boolean open = false;

    // priority 2
    private LocalTime openTime;
    private LocalTime closeTime;

    @Lob
    private String aboutRestaurant;


}
