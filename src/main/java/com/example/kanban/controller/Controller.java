package com.example.kanban.controller;

import com.example.kanban.exception.InvalidUserException;
import com.example.kanban.exception.NotFoundException;

public interface Controller<T> {
    Iterable<T> getAll();

    T getById(Long id) throws NotFoundException;

    T create(T content) throws InvalidUserException, NotFoundException;

    T update(Long id, T content) throws InvalidUserException, NotFoundException;

    void delete(Long id);
}
