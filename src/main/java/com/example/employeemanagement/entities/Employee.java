package com.example.employeemanagement.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class Employee {

    private UUID id;
    @NotNull(message = "First Name Is Required.")
    private String firstName;

    @NotNull(message = "Last Name Is Required.")
    private String lastName;

    @NotNull(message = "E-mail Is Required.")
    @Email(message = "Invalid E-mail Format")
    private String email;

    @NotNull(message = "Phone Number Is Required.")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid Phone Number Format")
    private String phoneNumber;

    @NotNull(message = "Hire Date Is Required.")
    @PastOrPresent(message = "Hire Date Must be Past or Present")
    private LocalDate hireDate;

    @NotNull(message = "Position Is Required.")
    private String position;

    private UUID departmentId;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UUID)) return false;

        return id.equals(obj);
    }

    public void update(Employee emp) {
        firstName = emp.getFirstName();
        lastName = emp.getLastName();
        email = emp.getEmail();
        phoneNumber = emp.getPhoneNumber();
        hireDate = emp.getHireDate();
        position = emp.getPosition();
    }

}
