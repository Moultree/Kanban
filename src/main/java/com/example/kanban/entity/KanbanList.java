package com.example.kanban.entity;

import java.util.List;
import jakarta.persistence.*;

/**
 * This class represents a list in a Kanban board.
 * A list contains multiple cards and belongs to a single board.
 */
@Entity
@Table(name = "lists")
public class KanbanList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    // constructors
    public KanbanList() {
    }

    public KanbanList(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    // toString method
    @Override
    public String toString() {
        return "List [id=" + id + ", name=" + name + ", cards=" + cards + ", board=" + board + "]";
    }
}