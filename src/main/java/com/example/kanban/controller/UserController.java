package com.example.kanban.controller;

import com.example.kanban.entity.User;
import com.example.kanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController implements Controller<User> {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository sourceRepository) {
        this.repository = sourceRepository;
    }

    @GetMapping("/")
    public List<User> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody User user) {
        repository.save(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

