package com.example.kanban.controller;

import com.example.kanban.entity.KanbanList;
import com.example.kanban.repository.KanbanListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lists")
public class KanbanListController implements Controller<KanbanList> {
    @Autowired
    private KanbanListRepository repository;

    public KanbanListController(KanbanListRepository sourceRepository){
        this.repository = sourceRepository;
    }

    @GetMapping("/")
    public List <KanbanList> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional <KanbanList> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody KanbanList kanbanList) {
        repository.save(kanbanList);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody KanbanList kanbanList) {
        kanbanList.setId(id);
        repository.save(kanbanList);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
