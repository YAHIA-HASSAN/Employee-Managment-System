package com.example.employeemanagement.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record SignUpRequest(

        @NotNull(message = "username is required")
        @Size(min = 2,max = 50, message = "min is 2 character and max is 50 character")
        String username,

        @NotNull(message = "password is required")
        @Size(min = 2,max = 50, message = "min is 2 character and max is 50 character")
        String password,

        @NotNull(message = "employee id is required")
        UUID employeeId

) {
}
