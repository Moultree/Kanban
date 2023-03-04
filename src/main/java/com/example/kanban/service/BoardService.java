package com.example.kanban.service;

import com.example.kanban.entity.Board;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.repository.BoardRepository;
import com.example.kanban.repository.UserRepository;
import com.example.kanban.service.interfaces.IBoardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService implements IBoardService {

    BoardRepository repository;
    UserRepository userRepository;

    public BoardService(BoardRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Board> getAllBoards() {
        return repository.findAll();
    }

    @Override
    public Board getBoardById(Long id) throws NotFoundException {
        Optional<Board> board = repository.findById(id);
        if (board.isEmpty()) {
            throw new NotFoundException("Board not found with id: " + id);
        }

        return board.get();
    }

    @Override
    public Board createBoard(Board board) throws NotFoundException {
        if (userRepository.findById(board.getAuthorId()).isEmpty()) {
            throw new NotFoundException("Author not found with id: " + board.getAuthorId());
        }

        return repository.save(board);
    }

    @Override
    public Board updateBoard(Long id, Board board) throws NotFoundException {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Board not found with id: " + id);
        }

        board.setId(id);
        return repository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        Optional<Board> board = repository.findById(id);
        board.ifPresent(b -> repository.delete(b));
    }
}
