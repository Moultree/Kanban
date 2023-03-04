package com.example.kanban.service.interfaces;

import com.example.kanban.entity.KanbanList;
import com.example.kanban.exception.NotFoundException;

import java.util.List;

public interface IKanbanListService {
    List<KanbanList> getAllKanbanLists();

    KanbanList getKanbanListById(Long id) throws NotFoundException;

    KanbanList createKanbanList(KanbanList list);

    KanbanList updateKanbanList(Long id, KanbanList list) throws NotFoundException;

    void deleteKanbanList(Long id);
}
