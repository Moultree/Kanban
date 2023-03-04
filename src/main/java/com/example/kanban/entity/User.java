package com.example.kanban.entity;

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
    private Long id;

    // TODO think about updating ownedBoards and invitedBoards
    @OneToMany(mappedBy = "authorId", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Board> ownedBoards = new ArrayList<>();
    @Column(name = "username", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain alphanumeric characters")
    private String username;
    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password can only contain alphanumeric characters")
    @Size(min = 8)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Invalid email address")
    private String email;

    @ManyToMany(mappedBy = "invitedUsers")
    private final List<Board> invitedBoards = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", ownedBoards=" + ownedBoards +
                ", invitedBoards=" + invitedBoards +
                '}';
    }
}