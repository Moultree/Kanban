package com.example.kanban.service.interfaces;

import com.example.kanban.entity.Card;
import com.example.kanban.exception.NotFoundException;

import java.util.List;

public interface ICardService {
    List<Card> getAllCards();

    Card getCardById(Long id) throws NotFoundException;

    Card createCard(Card card);

    Card updateCard(Long id, Card card) throws NotFoundException;

    void deleteCard(Long id);
}
