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
    /**
     * Unique identifier of board
     */
    private Long id;

    @Column(name = "name")
    /**
     * Board's name in application
     */
    private String name;

    @Column(name = "author_id")
    /**
     * Id of user owning the board
     */
    private Long authorId;

    @ManyToMany
    @JoinTable(
            name = "board_invitations",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"board_id", "user_id"})
    )
    /**
     * List of users who have access to the board via invites
     */
    private final List<User> invitedUsers = new ArrayList<>();

    public Board() {
    }

    /**
     * Creates Board entity
     * @param name Board name
     * @param authorId Id of user owning the board
     */
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

    public void addInvitedUser(User user) {
        invitedUsers.add(user);
    }

    public void removeInvitedUser(User user) {
        invitedUsers.remove(user);
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
