package com.substring.foodie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "foodie_restaurant")
@Getter
@Setter
public class Restaurant {

    @Id
    private String id;

    private String name;

    @Lob
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Boolean open = true;
    private String address;
    //TODO:
    private String banner;
    private LocalDateTime createdDate;



    @ManyToOne
    private User user;




}
