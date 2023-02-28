package com.example.kanban.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id")
    private KanbanList list;

    public Task() {
    }

    public Task(String title, Board board, KanbanList list) {
        this.title = title;
        this.board = board;
        this.list = list;
    }

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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public KanbanList getList() {
        return list;
    }

    public void setList(KanbanList list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", board=" + board + ", column=" + list + "]";
    }
}