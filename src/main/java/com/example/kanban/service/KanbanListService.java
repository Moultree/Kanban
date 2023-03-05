package com.example.kanban.service;

import com.example.kanban.entity.KanbanList;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.repository.KanbanListRepository;
import com.example.kanban.service.interfaces.IKanbanListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KanbanListService implements IKanbanListService {

    private KanbanListRepository repository;

    @Autowired
    public KanbanListService(KanbanListRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<KanbanList> getAllKanbanLists() {
        return repository.findAll();
    }

    @Override
    public KanbanList getKanbanListById(Long id) throws NotFoundException {
        Optional<KanbanList> list = repository.findById(id);
        if (list.isPresent()) {
            return list.get();
        } else {
            throw new NotFoundException("List not found with id: " + id);
        }
    }

    @Override
    public KanbanList createKanbanList(KanbanList list) {
        return repository.save(list);
    }

    @Override
    public KanbanList updateKanbanList(Long id, KanbanList list) throws NotFoundException {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Cannot update list that doesn't exist");
        }

        list.setId(id);
        return repository.save(list);
    }

    @Override
    public void deleteKanbanList(Long id) {
        Optional<KanbanList> list = repository.findById(id);
        list.ifPresent(l -> repository.delete(l));
    }
}
