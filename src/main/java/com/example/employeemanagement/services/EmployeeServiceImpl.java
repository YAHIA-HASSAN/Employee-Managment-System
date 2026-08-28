package com.example.employeemanagement.services;

import com.example.employeemanagement.abstracts.EmployeeService;
import com.example.employeemanagement.dtos.EmployeeCreate;
import com.example.employeemanagement.dtos.EmployeeUpdate;
import com.example.employeemanagement.entities.Department;
import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.repositories.DepartmentRepo;
import com.example.employeemanagement.repositories.EmployeeRepo;
import com.example.employeemanagement.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee findOne(UUID employeeID) {
        Optional<Employee> employee = employeeRepo.findById(employeeID);
        if (employee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }
        return employee.get();
    }

    @Override
    public Employee createOne(EmployeeCreate employeeCreate) {

        Optional<Department> department = departmentRepo.findById(employeeCreate.departmentId());
        if(department.isEmpty()){
            throw CustomResponseException.ResourceNotFound("Department with id "+ employeeCreate.departmentId()+" not found");
        }

        Employee employee = new Employee(
                employeeCreate.firstName(),
                employeeCreate.lastName(),
                employeeCreate.email(),
                employeeCreate.phoneNumber(),
                employeeCreate.hireDate(),
                employeeCreate.position(),
                department.get()
        );

        employeeRepo.save(employee);

        return employee;
    }

    @Override
    public Employee updateOne(UUID employeeID, EmployeeUpdate newEmployee) {
        Optional<Employee> existingEmployee = employeeRepo.findById(employeeID);


        if (existingEmployee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }

        existingEmployee.get().update(newEmployee);
        employeeRepo.save(existingEmployee.get());
        return existingEmployee.get();
    }

    @Override
    public void deleteOne(UUID employeeID) {
        Optional<Employee> employee = employeeRepo.findById(employeeID);
        if (employee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }

        employeeRepo.deleteById(employee.get().getId());
    }
}
