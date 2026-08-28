package com.example.employeemanagement.shared;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionResponse {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceException(NoResourceFoundException ex) {
        var errors = List.of(new GlobalResponse.ErrorItem("Resource is not found"));
        return new ResponseEntity<>(new GlobalResponse<>(errors), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<?> handleCustomResponseException(CustomResponseException ex) {
        var errors = List.of(new GlobalResponse.ErrorItem(ex.getMessage()));
        return new ResponseEntity<>(new GlobalResponse<>(errors), HttpStatus.resolve(ex.getCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new GlobalResponse.ErrorItem(err.getField() + " " + err.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(new GlobalResponse<>(errors), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GlobalResponse<?>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {

        String message = "Data already exists";

        Throwable cause = ex;

        while (cause.getCause() != null) {
            cause = cause.getCause();
        }

        if (cause instanceof org.postgresql.util.PSQLException psqlException) {

            var serverError = psqlException.getServerErrorMessage();

            if (serverError != null) {
                String constraint = serverError.getConstraint();

                if ("employees_email_key".equals(constraint)) {
                    message = "An employee with this email already exists";
                }
            }
        }

        var errors = List.of(
                new GlobalResponse.ErrorItem(message)
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new GlobalResponse<>(errors));
    }

}
