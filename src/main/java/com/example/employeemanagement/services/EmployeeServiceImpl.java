package com.example.employeemanagement.services;

import com.example.employeemanagement.abstracts.EmployeeService;
import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.shared.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public ArrayList<Employee> findAll(){
        return employees;
    }

    @Override
    public Employee findOne(UUID employeeID) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        if (employee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }
        return employee.get();
    }

    @Override
    public Employee createOne(Employee employee) {

        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());

        employees.add(employee);
        return employees.getLast();
    }

    @Override
    public Employee updateOne(UUID employeeID, Employee newEmployee){
        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();


        if (existingEmployee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }

        existingEmployee.get().update(newEmployee);
        return existingEmployee.get();
    }

    @Override
  public void  deleteOne(UUID employeeID){
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        if (employee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }

        employees.remove(employee.get());
    }
}
