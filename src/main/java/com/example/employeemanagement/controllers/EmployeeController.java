package com.example.employeemanagement.controllers;

import com.example.employeemanagement.abstracts.EmployeeService;
import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.shared.CustomResponseException;
import com.example.employeemanagement.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid Employee employee) {
        Employee returnEmployee = employeeService.createOne(employee);
        return new ResponseEntity<>(new GlobalResponse<>(returnEmployee), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> findAll() {
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
            @RequestBody Employee newEmployee) {
        return new ResponseEntity<>(
                new GlobalResponse<>(employeeService.updateOne(employeeID, newEmployee)),
                HttpStatus.OK
        );

    }


    @DeleteMapping("/{employeeID}")
    public ResponseEntity<GlobalResponse> deleteOne(@PathVariable UUID employeeID) {
        employeeService.deleteOne(employeeID);
        return ResponseEntity.noContent().build();
    }


}