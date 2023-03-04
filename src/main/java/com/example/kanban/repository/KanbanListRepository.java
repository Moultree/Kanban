package com.example.kanban.repository;

import com.example.kanban.entity.KanbanList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanListRepository extends JpaRepository<KanbanList, Long> {
}
