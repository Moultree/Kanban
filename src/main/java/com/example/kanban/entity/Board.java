package com.example.kanban.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "author_id")
    private Long authorId;

    @ManyToMany
    @JoinTable(
            name = "board_invitations",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private final List<User> invitedUsers = new ArrayList<>();

    public Board() {
    }

    public Board(String name, Long authorId) {
        this.name = name;
        this.authorId = authorId;
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

    public Long getAuthorId() {
        return authorId;
    }

    public List<User> getInvitedUsers() {
        return invitedUsers;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + authorId +
                ", invitedUsers=" + invitedUsers +
                '}';
    }
}
