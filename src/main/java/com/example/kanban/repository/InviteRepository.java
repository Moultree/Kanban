package com.example.kanban.repository;

import com.example.kanban.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InviteRepository extends JpaRepository<Invite, Long> {
    List<Invite> findByExpirationDateBefore(LocalDateTime now);

    Optional<Invite> findByToken(String token);
}
