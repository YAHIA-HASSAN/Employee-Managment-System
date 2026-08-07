package com.example.employeemanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

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
    private UUID departmentId;
    private String position;

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
