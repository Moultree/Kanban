package com.example.kanban.exception;

public class UnauthorizedUserException extends Exception{
    public UnauthorizedUserException() {
    }
    public UnauthorizedUserException(String message) {
        super(message);
    }
}
