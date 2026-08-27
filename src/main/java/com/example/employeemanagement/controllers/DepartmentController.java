package com.example.employeemanagement.controllers;

import com.example.employeemanagement.abstracts.DepartmentService;
import com.example.employeemanagement.dtos.DepartmentCreate;
import com.example.employeemanagement.entities.Department;
import com.example.employeemanagement.shared.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> findAll() {
        return new ResponseEntity<>(new GlobalResponse<>(departmentService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{departmentID}")
    public ResponseEntity<GlobalResponse<Department>> findOne(@PathVariable UUID departmentID) {
        Department department = departmentService.findOne(departmentID);

        return new ResponseEntity<>(new GlobalResponse<>(department), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Department>> createOne(@RequestBody DepartmentCreate department) {
        Department returnDepartment = departmentService.createOne(department);
        return new ResponseEntity<>(new GlobalResponse<>(returnDepartment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{departmentID}")
    public ResponseEntity<GlobalResponse> deleteOne(@PathVariable UUID departmentID) {
        departmentService.deleteOne(departmentID);
        return ResponseEntity.noContent().build();
    }


}
