package com.example.employeemanagement.controllers;

import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.shared.CustomResponseException;
import com.example.employeemanagement.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;
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
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());

        employees.add(employee);

        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> findAll() {
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeID) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        if (employee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }

        return new ResponseEntity<>(new GlobalResponse<>(employee.get()), HttpStatus.OK);
    }

    @PutMapping("/{employeeID}")
    public ResponseEntity<GlobalResponse<Employee>> updateOne(
            @PathVariable UUID employeeID,
            @RequestBody Employee newEmployee) {


        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();


        if (existingEmployee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }

        existingEmployee.get().update(newEmployee);
        return new ResponseEntity<>(new GlobalResponse<>(existingEmployee.get()), HttpStatus.OK);

    }


    @DeleteMapping("/{employeeID}")
    public ResponseEntity<GlobalResponse> deleteOne(@PathVariable UUID employeeID) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        if (employee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }

        employees.remove(employee.get());

        return ResponseEntity.noContent().build();
    }


}