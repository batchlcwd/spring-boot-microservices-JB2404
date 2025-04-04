package com.substring.foodie.dto;

import com.substring.foodie.entity.Address;
import com.substring.foodie.entity.FoodItem;
import com.substring.foodie.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantDto {

    private String id;
    private String name;
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Boolean open = true;
    private AddressDto address;
    private UserDto owner;
    private boolean isActive = true;
    private LocalDateTime createdDate;
    private List<FoodItemDto> foodItems = new ArrayList<>();
    private String bannerImageUrl;

}
