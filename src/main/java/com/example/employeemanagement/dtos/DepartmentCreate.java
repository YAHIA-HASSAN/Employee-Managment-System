package com.example.employeemanagement.dtos;

import jakarta.validation.constraints.NotNull;

public record DepartmentCreate(

        @NotNull(message = "Department name is required")
        String name
) {
}

