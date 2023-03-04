package com.example.kanban.service;

import com.example.kanban.entity.Board;
import com.example.kanban.entity.Link;
import com.example.kanban.entity.User;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.repository.BoardRepository;
import com.example.kanban.repository.LinkRepository;
import com.example.kanban.repository.UserRepository;
import com.example.kanban.service.interfaces.ILinkService;

import java.util.List;
import java.util.Optional;

public class LinkService implements ILinkService {

    LinkRepository repository;
    BoardRepository boardRepository;
    UserRepository userRepository;

    public LinkService(LinkRepository repository, BoardRepository boardRepository, UserRepository userRepository){
        this.repository = repository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List <Link> getAllLinks() {
        return this.repository.findAll();
    }

    @Override
    public Link getLinkById(Long id) throws NotFoundException {
        Optional<Link> link = repository.findById(id);
        if (link.isEmpty()){
            throw new NotFoundException("Link not found with id: "+id);
        }

        return link.get();
    }

    @Override
    public Link createLink(Link link) throws NotFoundException {
        if (userRepository.findById(link.getAuthorId()).isEmpty()){
            throw new NotFoundException("Author not found with id: " + link.getAuthorId());

        }
        return repository.save(link);
    }

    @Override
    public void deleteLink(Long id) {
        Optional<Link> link = repository.findById(id);
        link.ifPresent(l -> repository.delete(l));
    }
}
