package com.example.employeemanagement.entities;

import com.example.employeemanagement.dtos.EmployeeUpdate;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, length = 200, unique = true)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "department_id", nullable = false)
    private UUID departmentId = UUID.randomUUID();


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
