package com.example.employeemanagement.repositories;


import com.example.employeemanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {


}
