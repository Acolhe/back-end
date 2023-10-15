package com.example.colheapi.Classes;

public class ApiResponse<T> {
    private String message;
    private T data; // Adicionando um campo de dados para armazenar o objeto

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    // Getters e setters para a mensagem e o objeto de dados
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

