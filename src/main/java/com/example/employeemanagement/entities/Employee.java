package com.example.employeemanagement.entities;

import com.example.employeemanagement.dtos.EmployeeUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


import java.time.LocalDate;
import java.util.Date;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonProperty(value = "departmentId")
    private Department department;


    public Employee(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            LocalDate hireDate,
            String position,
            Department department) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.position = position;
        this.department = department;

    }

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

    public UUID getDepartment(){
        return department.getId();
    }

}
