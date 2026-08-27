package com.example.employeemanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Entity
@Table(name = "department")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Department {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;


    public Department(String name) {
        this.name = name;
    }
}
