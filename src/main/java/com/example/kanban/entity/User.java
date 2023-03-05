package com.example.kanban.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user of the Kanban board service.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Unique identifier of user
     */
    private Long id;


    @OneToMany(mappedBy = "authorId", cascade = CascadeType.ALL, orphanRemoval = true)
    /**
     * List of kanban boards owned by user
     */
    private final List<Board> ownedBoards = new ArrayList<>();

    @Column(name = "username", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain alphanumeric characters")
    /**
     *
     */
    private String username;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password can only contain alphanumeric characters")
    @Size(min = 8)
    /**
     *
     */
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Invalid email address")
    /**
     *
     */
    private String email;

    @ManyToMany(mappedBy = "invitedUsers")
    /**
     * List of kanban board user has access to
     */
    private final List<Board> invitedBoards = new ArrayList<>();

    public User() {
    }

    /**
     * Creates User entity
     * @param username Username used in application and database. Required to log in
     * @param password
     * @param email Email
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     *
     * @return id of User
     */
    public Long getId() {
        return id;
    }

    /**
     * Set user's id or change existing
     * @param id new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user's username in application
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set user's username or change existing
     * @param username new username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return user's password in application
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user's password or change existing
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return user's email in application
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user's email or change existing
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Add board to list of boards user owns
     * @param board Board instance
     */
    public void addOwnedBoard(Board board) {
        ownedBoards.add(board);
    }

    /**
     * Remove chosen board from user's possession
     * @param board Board instance
     */
    public void removeOwnedBoard(Board board) {
        ownedBoards.remove(board);
    }

    /**
     * Add board to list of board user has access to
     * @param board
     */
    public void addInvitedBoard(Board board) {
        invitedBoards.add(board);
        board.addInvitedUser(this);
    }

    /**
     * Remove chosen board from user's access
     * @param board
     */
    public void removeInvitedBoard(Board board) {
        invitedBoards.remove(board);
        board.removeInvitedUser(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", ownedBoards=" + ownedBoards +
                ", invitedBoards=" + invitedBoards +
                '}';
    }
}