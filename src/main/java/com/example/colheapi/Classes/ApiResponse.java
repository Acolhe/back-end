package com.example.colheapi.Classes;

public class ApiResponse<T> {
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

