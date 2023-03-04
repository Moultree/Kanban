package com.example.kanban.service;

import com.example.kanban.entity.User;
import com.example.kanban.exception.InvalidUserException;
import com.example.kanban.exception.UserNotFoundException;
import com.example.kanban.repository.UserRepository;
import com.example.kanban.service.interfaces.IUserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {

    UserRepository repository;

    ValidatorFactory validatorFactory;

    @Autowired
    public UserService(UserRepository repository, ValidatorFactory validatorFactory) {
        this.repository = repository;
        this.validatorFactory = validatorFactory;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public User createUser(User user) throws InvalidUserException {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new InvalidUserException("Cannot create user with given parameters");
        }

        return repository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) throws InvalidUserException {
        if (repository.findById(id).isEmpty()) {
            throw new InvalidUserException("Cannot update user that doesn't exist");
        }

        user.setId(id);
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = repository.findById(id);
        user.ifPresent(u -> repository.delete(u));
    }

    public void deleteUser(String username) {
        Optional<User> user = repository.findByUsername(username);
        user.ifPresent(u -> repository.delete(u));
    }
}
