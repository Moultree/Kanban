package com.example.kanban.service;

import com.example.kanban.entity.Card;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.repository.CardRepository;
import com.example.kanban.service.interfaces.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService implements ICardService {

    private CardRepository repository;

    @Autowired
    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Card> getAllCards() {
        return repository.findAll();
    }

    @Override
    public Card getCardById(Long id) throws NotFoundException {
        Optional<Card> card = repository.findById(id);
        if (card.isPresent()) {
            return card.get();
        } else {
            throw new NotFoundException("Card not found with id: " + id);
        }
    }

    @Override
    public Card createCard(Card card) {
        return repository.save(card);
    }

    @Override
    public Card updateCard(Long id, Card card) throws NotFoundException {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Cannot update card that doesn't exist");
        }

        card.setId(id);
        return repository.save(card);
    }

    @Override
    public void deleteCard(Long id) {
        Optional<Card> card = repository.findById(id);
        card.ifPresent(c -> repository.delete(c));
    }
}
