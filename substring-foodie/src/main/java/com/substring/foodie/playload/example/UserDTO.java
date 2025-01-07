package com.substring.foodie.playload.example;

import com.substring.foodie.utils.ValidGender;
import jakarta.validation.constraints.*;

public class UserDTO {

    @NotEmpty(message = "Name is required !!")
    @Size(min = 3, max = 20, message = "Name must be between 2 and 20 characters")
    //must contain one Caps Letter
    //must contain one digit
    //must contain one special character
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "Invalid username. Username must contain 1 Capital Letter, 1 Digit and 1 Special Character")
    private String name;
    @Min(value = 18, message = "Minimum value required is 18")
    @Max(value = 99, message = "Maximum value required is 99")
    private int age;
    @Email(message = "Invalid email !!")
    private String email;
    @NotEmpty(message = "Password is required !!")
    private String password;


    @ValidGender(message = "Only male and female are allowed !!")
    private String gender;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
