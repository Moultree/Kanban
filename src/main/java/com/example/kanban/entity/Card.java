package com.example.kanban.entity;

import jakarta.persistence.*;

/**
 * Represents a card in a Kanban board.
 * A card contains a title and description, and belongs to a list.
 */
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "position")
    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id")
    private KanbanList list;

    // constructors
    public Card() {
    }

    public Card(String title, String description, KanbanList list, int position) {
        this.title = title;
        this.description = description;
        this.list = list;
        this.position = position;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KanbanList getList() {
        return list;
    }

    public void setList(KanbanList list) {
        this.list = list;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // toString method
    @Override
    public String toString() {
        return "Card [id=" + id + ", title=" + title + ", description=" + description + ", list=" + list + "]";
    }
}