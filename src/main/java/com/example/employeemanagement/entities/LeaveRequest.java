package com.example.employeemanagement.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "leave_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date",nullable = false)
    private LocalDate endDate;

    @Column(name = "reason",columnDefinition = "TEXT")
    private String reason;

    @Column(name = "status",nullable = false, length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public UUID getEmployee(){
        return employee.getId();
    }

    public LeaveRequest(LocalDate startDate,LocalDate endDate, String reason, Employee employee){
        this.startDate = startDate;
        this.endDate= endDate;
        this.reason=reason;
        this.employee = employee;
        this.status = "PENDING";
    }
}
