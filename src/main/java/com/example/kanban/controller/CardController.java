package com.example.kanban.controller;

import com.example.kanban.entity.Card;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.service.interfaces.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController implements Controller<Card> {

    private final ICardService service;

    @Autowired
    public CardController(ICardService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Card> getAll() {
        return service.getAllCards();
    }

    @GetMapping("/{id}")
    public Card getById(@PathVariable Long id) throws NotFoundException {
        return service.getCardById(id);
    }

    @PostMapping("/")
    public Card create(@RequestBody Card card) {
        return service.createCard(card);
    }

    @PutMapping("/{id}")
    public Card update(@PathVariable Long id, @RequestBody Card card) throws NotFoundException {
        return service.updateCard(id, card);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCard(id);
    }
}
