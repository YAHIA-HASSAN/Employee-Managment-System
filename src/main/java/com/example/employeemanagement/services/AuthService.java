package com.example.employeemanagement.services;


import com.example.employeemanagement.dtos.SignUpRequest;
import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.entities.UserAccount;
import com.example.employeemanagement.repositories.EmployeeRepo;
import com.example.employeemanagement.repositories.UserAccountRepo;
import com.example.employeemanagement.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserAccountRepo userAccountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepo employeeRepo;


    public void signUp(SignUpRequest signUpRequest) {

        Employee employee = employeeRepo.findById(signUpRequest.employeeId())
                .orElseThrow(()-> CustomResponseException.ResourceNotFound(
                        "Employee with id "+signUpRequest.employeeId() + " not found"
                ));

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(signUpRequest.username());
        userAccount.setPassword(passwordEncoder.encode(signUpRequest.password()));
        userAccount.setEmployee(employee);


        userAccountRepo.save(userAccount);
    }
}
