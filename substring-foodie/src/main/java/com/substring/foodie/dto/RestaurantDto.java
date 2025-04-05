package com.substring.foodie.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Restaurant name is mandatory")
    @Size(max = 100, message = "Restaurant name must be less than 100 characters")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Open time is mandatory")
    private LocalTime openTime;

    @NotNull(message = "Close time is mandatory")
    private LocalTime closeTime;

    @NotNull(message = "Open status is mandatory")
    private Boolean open = true;

    @NotNull(message = "Address is mandatory")
    private AddressDto address;

    @NotNull(message = "Owner is mandatory")
    private UserDto owner;

    @NotNull(message = "Active status is mandatory")
    private boolean isActive = true;

    @NotNull(message = "Created date is mandatory")
    private LocalDateTime createdDate;

    @Size(max = 255, message = "Banner image URL must be less than 255 characters")
    private String bannerImageUrl;

}

