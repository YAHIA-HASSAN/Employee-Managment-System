package com.example.employeemanagement.abstracts;


import com.example.employeemanagement.dtos.LeaveRequestCreate;
import com.example.employeemanagement.entities.LeaveRequest;

import java.util.List;
import java.util.UUID;

public interface LeaveRequestService {
    List<LeaveRequest> findAllByEmployeeId(UUID employeeID);

    LeaveRequest findOne(UUID leaveRequestID);

    LeaveRequest createOne(LeaveRequestCreate leaveRequest, UUID employeeId);

 }
