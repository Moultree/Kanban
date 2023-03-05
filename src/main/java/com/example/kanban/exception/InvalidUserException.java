package com.example.kanban.exception;

public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException() {
    }
}
