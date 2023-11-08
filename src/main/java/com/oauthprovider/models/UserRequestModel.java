package com.oauthprovider.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestModel {

    @NotNull (message = "firstName is required")
    @NotBlank (message = "firstName should not be empty")
    private String firstName;

    @NotNull (message = "lastName is required")
    @NotBlank (message = "lastName should not be empty")
    private String lastName;

    @NotNull (message = "email is required")
    @NotBlank (message = "email should not be empty")
    private String email;
    
}