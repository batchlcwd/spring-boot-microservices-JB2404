package com.result.view.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentForm {
    @NotBlank(message = "Name is  required !!")
    private String name;
    @NotBlank(message = "Roll Number is  required !!")
    private String rollNumber;
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid Email !!")
    private String email;
    @NotBlank(message = "Address is required !!")
    private String address;
    @NotBlank(message = "School is required !!")
    private String schoolName;
    private String photoName;
//    @NotBlank(message = "Dob is required !!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Standard is required !!")
    private String standard;
    @NotBlank(message = "FatherName is required !!")
    private String fatherName;
    @NotBlank(message = "Gender is required !!")
    private String gender;
}
