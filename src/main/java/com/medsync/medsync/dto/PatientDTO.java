package com.medsync.medsync.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientDTO {

    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Social Security Number is mandatory")
    @Pattern(regexp = "^[A-Z0-9-]*$", message = "SSN must be alphanumeric")
    private String socialSecurityNumber;

    private String gender;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid international phone number format")
    private String phoneNumber;
}