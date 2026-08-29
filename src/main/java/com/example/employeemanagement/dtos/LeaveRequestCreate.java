package com.example.employeemanagement.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record LeaveRequestCreate (
        @NotNull(message = "Start Date Is Required")
        @FutureOrPresent(message = "Start Date Can't be in The Past")
        LocalDate startDate,

        @NotNull(message = "End Date Is Required")
        @FutureOrPresent(message = "End Date Can't be in The Past")
        LocalDate endDate,

        @NotNull(message = "Reason Is Required")
        @Size(min = 5, max = 100, message = "min is 5 characters and max is 100 characters")
        String reason
){
}
