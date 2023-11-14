package com.example.fooddelivery.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.fooddelivery.model.User}
 */
@Value
public class UserRegistrationDto implements Serializable {
    @NotNull
    @NotBlank
    @Size(min=3, max=255)
    String username;


    @NotNull
    @NotBlank
    @Size(min=3, max=32)
    //Minimum six characters, at least one letter and one number
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
    String password;


    @Email
    @NotNull
    @NotBlank
    String email;


    String firstName;
    String lastName;
}