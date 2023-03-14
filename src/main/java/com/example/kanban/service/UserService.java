package com.example.kanban.service;

import com.example.kanban.entity.User;
import com.example.kanban.exception.InvalidUserException;
import com.example.kanban.exception.NotFoundException;
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
/**
 * Service class used to work with User entities
 * Can be used to create, find, update and delete them
 */
public class UserService implements IUserService {

    private final UserRepository repository;

    private final ValidatorFactory validatorFactory;

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
    public User getUserById(Long id) throws NotFoundException {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public User getUserByUsername(String username) throws NotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found with username: " + username);
        }
    }

    /**
     * Creates new User
     * @param user User instance
     * @return New User entry if validation was successful, raises exception otherwise
     * @throws InvalidUserException
     */
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
    public User updateUser(Long id, User user) throws NotFoundException {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Cannot update user that doesn't exist");
        }

        user.setId(id);
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = repository.findById(id);
        user.ifPresent(u -> repository.delete(u));
    }

}
