package com.example.kanban.entity;

import jakarta.persistence.Column;

import java.util.Random;

public class Link {
    //TODO add RNG to link id
    final private long DEFAULT_TIME = 300;
    @Column(name = "author_id", nullable = false)
    private long authorId;
    @Column(name = "board_id", nullable = false)
    private long boardId;
    @Column(name = "link_id", nullable = false, unique = true)
    private long id;

    private long TTL;

    public Link(long userId, long boardId)
    {
        this.authorId = userId;
        this.boardId = boardId;
        this.TTL = DEFAULT_TIME;
    }

    public long getId() {
        return id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public long getBoardId() {
        return boardId;
    }

    public long getTTL() {
        return TTL;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setTTL(long TTL) {
        this.TTL = TTL;
    }

    @Override
    public String toString() {
        return  "Link{"+
                "linkID= " + id +
                "creatorID= " + authorId +
                ", boardId= "+ boardId + '}';
    }
}
