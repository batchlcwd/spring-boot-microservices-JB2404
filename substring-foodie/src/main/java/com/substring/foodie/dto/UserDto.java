package com.substring.foodie.dto;

import com.substring.foodie.entity.Address;
import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{


    private String id;


    private String name;


    private String email;


    private String password;

//    private String phoneNumber;


    private Role role; // ADMIN, USER, DELIVERY_BOY, RESTAURANT

    private boolean isAvailable = true; // applicable for delivery boy

    // feel free to add more fields ad required

    private LocalDate createdDate;

    private boolean enabled = true;

    private List<RestaurantDto> restaurants = new ArrayList<>();


    private List<AddressDto> addresses = new ArrayList<>();

}
