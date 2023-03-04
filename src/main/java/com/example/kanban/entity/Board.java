package com.example.kanban.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a board in the Kanban board service.
 * A board can have multiple lists, and is owned by a single user.
 */
@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToMany
    @JoinTable(
            name = "board_invitations",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> invitedUsers = new ArrayList<>();

    public Board() {
    }

    public Board(String name) {
        this.name = name;
    }

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String toString() {
        return "Board [id=" + id + ", name=" + name + ", tasks=" + tasks + "]";
    }
}
