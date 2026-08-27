package com.example.employeemanagement.controllers;

import com.example.employeemanagement.abstracts.EmployeeService;
import com.example.employeemanagement.dtos.EmployeeCreate;
import com.example.employeemanagement.dtos.EmployeeUpdate;
import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid EmployeeCreate employee) {
        Employee returnEmployee = employeeService.createOne(employee);
        return new ResponseEntity<>(new GlobalResponse<>(returnEmployee), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<GlobalResponse<List<Employee>>> findAll() {
        return new ResponseEntity<>(new GlobalResponse<>(employeeService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeID) {
        Employee employee = employeeService.findOne(employeeID);

        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PutMapping("/{employeeID}")
    public ResponseEntity<GlobalResponse<Employee>> updateOne(
            @PathVariable UUID employeeID,
            @RequestBody EmployeeUpdate newEmployeeData) {
        return new ResponseEntity<>(
                new GlobalResponse<>(employeeService.updateOne(employeeID, newEmployeeData)),
                HttpStatus.OK
        );

    }


    @DeleteMapping("/{employeeID}")
    public ResponseEntity<GlobalResponse> deleteOne(@PathVariable UUID employeeID) {
        employeeService.deleteOne(employeeID);
        return ResponseEntity.noContent().build();
    }


}