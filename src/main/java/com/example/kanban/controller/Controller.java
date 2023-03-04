package com.example.kanban.controller;

import com.example.kanban.entity.Board;

import java.lang.reflect.Type;
import java.util.Optional;

public interface Controller<T> {
    public Iterable<T> getAll();
    public Optional <T> getById(Long id);
    public void add(T content);
    public void update(Long id, T content);
    public void delete(Long id);
}
