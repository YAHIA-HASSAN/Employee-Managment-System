package com.example.employeemanagement.controllers;

import com.example.employeemanagement.entities.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>(
            List.of(
                    new Employee(
                            UUID.randomUUID(),
                            "Yahi",
                            "Hassan",
                            "yahia@fds.com",
                            "01092879387",
                            LocalDate.of(2024, 3, 2),
                            UUID.randomUUID(),
                            "Junior"
                    )
            )
    );

    @GetMapping
    public ArrayList<Employee> findAll() {
        return employees;
    }

    @GetMapping("{employeeId}")
    public Optional<Employee> findOne(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId)).findFirst();

        return employee;
    }
}
