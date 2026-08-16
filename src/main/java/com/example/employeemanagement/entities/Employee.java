package com.example.employeemanagement.entities;

import com.example.employeemanagement.dtos.EmployeeUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class Employee {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String position;
    private UUID departmentId;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UUID)) return false;

        return id.equals(obj);
    }

    public void update(EmployeeUpdate emp) {
        firstName = !emp.firstName().isEmpty() ? emp.firstName() : firstName;
        lastName = !emp.lastName().isEmpty() ? emp.lastName() : lastName;
        phoneNumber = !emp.phoneNumber().isEmpty() ? emp.phoneNumber() : phoneNumber;
        position = !emp.position().isEmpty() ? emp.position() : position;
    }

}
