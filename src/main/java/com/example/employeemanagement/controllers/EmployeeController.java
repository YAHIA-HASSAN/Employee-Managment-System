package com.example.employeemanagement.controllers;

import com.example.employeemanagement.entities.Employee;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();


    @PostMapping
    public Employee createOne(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());

        employees.add(employee);

        return employee;
    }


    @GetMapping
    public ArrayList<Employee> findAll() {
        return employees;
    }

    @GetMapping("/{employeeID}")
    public Optional<Employee> findOne(@PathVariable UUID employeeID) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        return employee;
    }

    @PutMapping("/{employeeID}")
    public Optional<Employee> updateOne(
            @PathVariable UUID employeeID,
            @RequestBody Employee newEmployee) {

        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        if (existingEmployee.isPresent()) {
            existingEmployee.get().update(newEmployee);
        }

        return existingEmployee;
    }


    @DeleteMapping("/{employeeID}")
    public void deleteOne(@PathVariable UUID employeeID) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        if (employee.isPresent()) {
            employees.remove(employee.get());
        }

    }


}