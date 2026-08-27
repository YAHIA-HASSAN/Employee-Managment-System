package com.example.employeemanagement.abstracts;

import com.example.employeemanagement.dtos.DepartmentCreate;
import com.example.employeemanagement.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    Department findOne(UUID departmentID);

    List<Department> findAll();

    Department createOne(DepartmentCreate department);

    void deleteOne(UUID departmentID);
}
