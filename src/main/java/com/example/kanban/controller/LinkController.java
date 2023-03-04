package com.example.kanban.controller;

import com.example.kanban.entity.Link;
import com.example.kanban.exception.InvalidUserException;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.service.interfaces.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/links")
public class LinkController implements Controller<Link>{

    private final ILinkService service;

    @Autowired
    public LinkController(ILinkService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Iterable getAll() {
        return service.getAllLinks();
    }

    @GetMapping("/{id}")
    public Link getById(@PathVariable Long id) throws NotFoundException {
        return service.getLinkById(id);
    }

    @PostMapping("/")
    public void create(@RequestBody Link link) throws NotFoundException {
        service.createLink(link);
    }

    @PutMapping("/{id}")
    public Link update(Long id, Link link) throws InvalidUserException, NotFoundException {
        return null;
        //TODO make update method/ something else
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        service.deleteLink(id);
    }
}
