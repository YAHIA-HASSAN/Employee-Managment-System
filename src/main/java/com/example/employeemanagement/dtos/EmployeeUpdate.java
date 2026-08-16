package com.example.employeemanagement.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record EmployeeUpdate(
        @NotNull(message = "First Name Is Required.")
        String firstName,

        @NotNull(message = "Last Name Is Required.")
        String lastName,

        @NotNull(message = "Phone Number Is Required.")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid Phone Number Format")
        String phoneNumber,

        @NotNull(message = "Position Is Required.")
        String position

) {
}
