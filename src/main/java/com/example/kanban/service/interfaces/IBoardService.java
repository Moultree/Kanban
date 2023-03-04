package com.example.kanban.service.interfaces;

import com.example.kanban.entity.Board;
import com.example.kanban.exception.NotFoundException;

import java.util.List;

public interface IBoardService {
    List<Board> getAllBoards();

    Board getBoardById(Long id) throws NotFoundException;

    Board createBoard(Board board) throws NotFoundException;

    Board updateBoard(Long id, Board board) throws NotFoundException;

    void deleteBoard(Long id);
}
