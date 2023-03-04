package com.example.kanban.controller;

import com.example.kanban.entity.Board;
import com.example.kanban.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boards")
public class BoardController implements Controller<Board> {

    private BoardRepository repository;

    @Autowired
    public BoardController(BoardRepository sourceRepository) {
        this.repository = sourceRepository;
    }

    @GetMapping("/")
    public List<Board> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Board> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Board board) {
        repository.save(board);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Board board) {
        board.setId(id);
        repository.save(board);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

