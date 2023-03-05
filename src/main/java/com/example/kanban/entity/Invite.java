package com.example.kanban.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "invites")
/**
 * Represents an invite in a Kanban board
 * Invite is used by board owner to give other users access to this board
 * Invites are not permanent and expire after certain amount of time
 */
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User invitedUser;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    public Invite() {
    }

    /**
     * Creates Invite entity
     * @param board Board to which access is granted
     * @param user User who can use this invite
     * Expiration time is set to default (24 hours)
     */
    public Invite(Board board, User user) {
        this.board = board;
        this.invitedUser = user;
        this.token = UUID.randomUUID().toString();
        this.expirationDate = LocalDateTime.now().plusHours(24);
    }

    /**
     * Creates Invite entity with custom expiration time
     * @param board Board to which access is granted
     * @param user User who can use this invite
     * @param expirationDate Time during which invite can be used
     */
    public Invite(Board board, User user, LocalDateTime expirationDate) {
        this.board = board;
        this.invitedUser = user;
        this.token = UUID.randomUUID().toString();
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public User getInvitedUser() {
        return invitedUser;
    }

    public void setInvitedUser(User invitedUser) {
        this.invitedUser = invitedUser;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
