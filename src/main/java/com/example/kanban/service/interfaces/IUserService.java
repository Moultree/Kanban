package com.example.kanban.service.interfaces;

import com.example.kanban.entity.User;
import com.example.kanban.exception.InvalidUserException;
import com.example.kanban.exception.NotFoundException;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User getUserById(Long id) throws NotFoundException;

    User getUserByUsername(String username) throws NotFoundException;

    User createUser(User user) throws InvalidUserException;

    User updateUser(Long id, User user) throws NotFoundException;

    void deleteUser(Long id);

    void deleteUser(String username);
}
