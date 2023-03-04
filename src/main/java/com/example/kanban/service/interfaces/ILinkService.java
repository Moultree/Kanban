package com.example.kanban.service.interfaces;

import com.example.kanban.entity.Board;
import com.example.kanban.entity.Link;
import com.example.kanban.entity.User;
import com.example.kanban.exception.NotFoundException;

import java.util.List;

public interface ILinkService {
    List <Link> getAllLinks();
    Link getLinkById(Long id) throws NotFoundException;
    Link createLink(Link link) throws NotFoundException;
    void deleteLink(Long id);
}
