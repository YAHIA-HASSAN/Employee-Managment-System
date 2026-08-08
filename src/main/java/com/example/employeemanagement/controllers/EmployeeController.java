package com.example.employeemanagement.controllers;

import com.example.employeemanagement.entities.Employee;
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
    public ResponseEntity<Employee> createOne(@RequestBody @Valid Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());

        employees.add(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<ArrayList<Employee>> findAll() {
        return new ResponseEntity<ArrayList<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<Employee> findOne(@PathVariable UUID employeeID) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<Employee>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/{employeeID}")
    public ResponseEntity<Employee> updateOne(
            @PathVariable UUID employeeID,
            @RequestBody Employee newEmployee) {


        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        if (existingEmployee.isPresent()) {
            existingEmployee.get().update(newEmployee);
            return new ResponseEntity<Employee>(existingEmployee.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{employeeID}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeID) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.equals(employeeID)).findFirst();

        try {
            if (employee.isPresent()) {
                employees.remove(employee.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}