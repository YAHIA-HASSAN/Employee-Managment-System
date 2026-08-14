package com.example.employeemanagement.abstracts;

import com.example.employeemanagement.entities.Employee;

import java.util.ArrayList;
import java.util.UUID;

public interface EmployeeService {
    ArrayList<Employee> findAll();

    Employee findOne(UUID employeeID);

    Employee createOne(Employee employee);

    Employee updateOne(UUID employeeID, Employee employee);

    void deleteOne(UUID employeeID);
}
