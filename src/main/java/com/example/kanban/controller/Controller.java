package com.example.kanban.controller;

import com.example.kanban.exception.InvalidUserException;
import com.example.kanban.exception.NotFoundException;

/**
 * Common interface for all controllers
 * @param <T> Type with which implementation works with
 */
public interface Controller<T> {
    /**
     * Get all instances from database
     * @return Iterable object containing all objects from database
     */
    Iterable<T> getAll();

    /**
     * Get instance from database by id
     * @param id Desired id of instance
     * @return Object with matching id or raise exception if none were found
     * @throws NotFoundException
     */
    T getById(Long id) throws NotFoundException;

    /**
     * Creates an instance in database from given object
     * @param content Object of corresponding type
     * @return Entry of database containing object or raises exception if creation was not successful
     * @throws InvalidUserException
     * @throws NotFoundException
     */
    T create(T content) throws InvalidUserException, NotFoundException;

    /**
     * Updates information about instance in database with given id
     * @param id Id of instance
     * @param content Object of corresponding type, which will be put in databse
     * @return Entry of database containing updated information or raises exception if update was not successful
     * @throws InvalidUserException
     * @throws NotFoundException
     */
    T update(Long id, T content) throws InvalidUserException, NotFoundException;

    /**
     * Deletes entry with given id from database
     * @param id Id of entry in database
     */
    void delete(Long id);
}
