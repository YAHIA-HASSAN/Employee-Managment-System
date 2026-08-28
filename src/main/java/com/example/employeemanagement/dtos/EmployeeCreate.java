package com.example.employeemanagement.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeeCreate(

        @NotNull(message = "First Name Is Required.")
        String firstName,

        @NotNull(message = "Last Name Is Required.")
        String lastName,

        @NotNull(message = "E-mail Is Required.")
        @Email(message = "Invalid E-mail Format")
        String email,

        @NotNull(message = "Phone Number Is Required.")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid Phone Number Format")
        String phoneNumber,

        @NotNull(message = "Hire Date Is Required.")
        @PastOrPresent(message = "Hire Date Must be Past or Present")
        LocalDate hireDate,

        @NotNull(message = "Position Is Required.")
        String position,

        @NotNull(message = "Department ID Is Required")
        UUID departmentId

) {
}
