package com.substring.foodie.playload.example;

import jakarta.validation.constraints.*;

public class UserDTO {

    @NotEmpty(message = "Name is required !!")
    @Size(min = 3, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;


    @Min(value = 18,message = "Minimum value required is 18")
    @Max(value = 99, message = "Maximum value required is 99")
    private int age;
    @Email(message = "Invalid email !!")
    private String email;

    @NotEmpty(message = "Password is required !!")
    private String password;

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
}
