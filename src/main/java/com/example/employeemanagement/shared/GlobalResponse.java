package com.example.employeemanagement.shared;

import lombok.Getter;

import java.util.List;


@Getter
public class GlobalResponse<T> {
    public final static String SUCCESS = "success";
    public final static String ERROR = "error";

    private final String status;
    private final T data;
    private final List<ErrorItem> errors;


    public record ErrorItem(String message) {
    }


    public GlobalResponse(List<ErrorItem> errors) {
        this.status = ERROR;
        this.data = null;
        this.errors = errors;
    }

    public GlobalResponse(T data) {
        this.status = SUCCESS;
        this.data = data;
        this.errors = null;
    }

}

