package com.example.employeemanagement.services;


import com.example.employeemanagement.abstracts.DepartmentService;
import com.example.employeemanagement.dtos.DepartmentCreate;
import com.example.employeemanagement.entities.Department;
import com.example.employeemanagement.repositories.DepartmentRepo;
import com.example.employeemanagement.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;


    @Override
    public Department findOne(UUID departmentID) {
        Optional<Department> department = departmentRepo.findById(departmentID);

        if (department.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Department with ID: " + departmentID + " not found!");
        }

        return department.get();
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public Department createOne(DepartmentCreate department) {

        Department newDepartment = new Department(department.name());
        departmentRepo.save(newDepartment);

        return newDepartment;
    }

    @Override
    public void deleteOne(UUID departmentID) {
        Optional<Department> department = departmentRepo.findById(departmentID);

        if (department.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Department with ID: " + departmentID + " not found!");
        }

        departmentRepo.deleteById(department.get().getId());

    }
}
