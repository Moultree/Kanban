package com.example.kanban.controller;

import java.util.Optional;

public interface Controller<T> {
    Iterable<T> getAll();

    Optional<T> getById(Long id);

    void add(T content);

    void update(Long id, T content);

    void delete(Long id);
}
