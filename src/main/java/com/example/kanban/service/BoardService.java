package com.example.kanban.service;

import com.example.kanban.entity.Board;
import com.example.kanban.entity.Invite;
import com.example.kanban.entity.User;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.repository.BoardRepository;
import com.example.kanban.repository.InviteRepository;
import com.example.kanban.repository.UserRepository;
import com.example.kanban.service.interfaces.IBoardService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
/**
 * Service class used to work with Board entities
 * Can create, find, update and delete them
 * Also used to work with invites, allowing to create invites and accepting them from users
 */
public class BoardService implements IBoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final InviteRepository inviteRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository, InviteRepository inviteRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.inviteRepository = inviteRepository;
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoardById(Long id) throws NotFoundException {
        Optional<Board> board = boardRepository.findById(id);
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

        return boardRepository.save(board);
    }

    @Override
    public Board updateBoard(Long id, Board board) throws NotFoundException {
        if (boardRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Board not found with id: " + id);
        }

        board.setId(id);
        return boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            userRepository.findById(board.get().getAuthorId()).ifPresent(u -> u.removeOwnedBoard(board.get()));
            boardRepository.delete(board.get());
        }
    }

    public String inviteUser(Long boardId, Long userId) throws NotFoundException {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException("Board not found with id: " + boardId));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        Invite invite = new Invite(board, user);

        board.addInvitedUser(user);

        inviteRepository.save(invite);
        boardRepository.save(board);

        return invite.getToken();
    }

    public void acceptInvitation(String token) throws NotFoundException {
        Invite invite = inviteRepository.findByToken(token).orElseThrow(() -> new NotFoundException("Invite not found with token: " + token));

        User user = invite.getInvitedUser();
        Board board = invite.getBoard();

        user.addInvitedBoard(board);
        userRepository.save(user);

        inviteRepository.delete(invite);
    }

    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void deleteExpiredInviteLinks() {
        LocalDateTime now = LocalDateTime.now();
        List<Invite> expiredInvites = inviteRepository.findByExpirationDateBefore(now);
        inviteRepository.deleteAll(expiredInvites);
    }
}
