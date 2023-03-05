package com.example.kanban.controller;

import com.example.kanban.entity.Board;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.service.interfaces.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController implements Controller<Board> {

    private final IBoardService service;

    @Autowired
    public BoardController(IBoardService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Board> getAll() {
        return service.getAllBoards();
    }

    @GetMapping("/{id}")
    public Board getById(@PathVariable Long id) throws NotFoundException {
        return service.getBoardById(id);
    }

    @PostMapping("/")
    public Board create(@RequestBody Board board) throws NotFoundException {
        return service.createBoard(board);
    }

    @PutMapping("/{id}")
    public Board update(@PathVariable Long id, @RequestBody Board board) throws NotFoundException {
        return service.updateBoard(id, board);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteBoard(id);
    }

    @GetMapping("/{boardId}/invite/{userId}")
    public ResponseEntity<String> generateInviteLink(@PathVariable Long boardId, @PathVariable Long userId) throws NotFoundException {
        return ResponseEntity.ok(service.inviteUser(boardId, userId));
    }

    @PostMapping("/invite/{token}")
    public ResponseEntity<String> acceptInvitation(@PathVariable String token) throws NotFoundException {
        service.acceptInvitation(token);

        return ResponseEntity.ok("Invitation accepted");
    }
}
