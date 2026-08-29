package com.example.employeemanagement.services;

import com.example.employeemanagement.abstracts.LeaveRequestService;
import com.example.employeemanagement.dtos.LeaveRequestCreate;
import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.entities.LeaveRequest;
import com.example.employeemanagement.repositories.EmployeeRepo;
import com.example.employeemanagement.repositories.LeaveRequestRepo;
import com.example.employeemanagement.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    LeaveRequestRepo leaveRequestRepo;
    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public List<LeaveRequest> findAllByEmployeeId(UUID employeeID) {
        return leaveRequestRepo.findAllByEmployeeId(employeeID);
    }

    @Override
    public LeaveRequest findOne(UUID leaveRequestID) {
        Optional<LeaveRequest> leaveRequest = leaveRequestRepo.findById(leaveRequestID);
        if(leaveRequest.isEmpty()){
            throw CustomResponseException.ResourceNotFound("The leave request with ID: " + leaveRequestID+" is not found" );
        }

        return leaveRequest.get();
    }

    @Override
    public LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate, UUID employeeID) {
        Optional<Employee> employee = employeeRepo.findById(employeeID);
        if (employee.isEmpty()) {
            throw CustomResponseException.ResourceNotFound("Employee with id " + employeeID + " not found");
        }

        LeaveRequest leaveRequest = new LeaveRequest(
                leaveRequestCreate.startDate(),
                leaveRequestCreate.endDate(),
                leaveRequestCreate.reason(),
                employee.get()
        );

        leaveRequestRepo.save(leaveRequest);

        return leaveRequest;
    }
}
