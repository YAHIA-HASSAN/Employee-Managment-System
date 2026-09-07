package com.example.employeemanagement.controllers;


import com.example.employeemanagement.dtos.SignUpRequest;
import com.example.employeemanagement.services.AuthService;
import com.example.employeemanagement.shared.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<String>> signup(
            @RequestBody SignUpRequest signUpRequest
            ){

        authService.signUp(signUpRequest);
        return new ResponseEntity<>(new GlobalResponse("Signed up"), HttpStatus.CREATED);
    }

}
