package com.example.kanban.controller;

import com.example.kanban.entity.Card;
import com.example.kanban.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController implements Controller<Card> {

    private CardRepository repository;

    @Autowired
    public CardController(CardRepository sourceRepository) {
        this.repository = sourceRepository;
    }

    @GetMapping("/")
    public List<Card> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Card> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Card card) {
        repository.save(card);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Card card) {
        card.setId(id);
        repository.save(card);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
