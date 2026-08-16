package com.example.employeemanagement.abstracts;

import com.example.employeemanagement.dtos.EmployeeCreate;
import com.example.employeemanagement.dtos.EmployeeUpdate;
import com.example.employeemanagement.entities.Employee;

import java.util.ArrayList;
import java.util.UUID;

public interface EmployeeService {
    ArrayList<Employee> findAll();

    Employee findOne(UUID employeeID);

    Employee createOne(EmployeeCreate employee);

    Employee updateOne(UUID employeeID, EmployeeUpdate employee);

    void deleteOne(UUID employeeID);
}
