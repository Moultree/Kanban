package com.example.kanban.controller;

import com.example.kanban.entity.KanbanList;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.service.interfaces.IKanbanListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class KanbanListController implements Controller<KanbanList> {

    private final IKanbanListService service;

    @Autowired
    public KanbanListController(IKanbanListService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<KanbanList> getAll() {
        return service.getAllKanbanLists();
    }

    @GetMapping("/{id}")
    public KanbanList getById(@PathVariable Long id) throws NotFoundException {
        return service.getKanbanListById(id);
    }

    @PostMapping("/")
    public KanbanList create(@RequestBody KanbanList kanbanList) {
        return service.createKanbanList(kanbanList);
    }

    @PutMapping("/{id}")
    public KanbanList update(@PathVariable Long id, @RequestBody KanbanList kanbanList) throws NotFoundException {
        return service.updateKanbanList(id, kanbanList);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteKanbanList(id);
    }
}
