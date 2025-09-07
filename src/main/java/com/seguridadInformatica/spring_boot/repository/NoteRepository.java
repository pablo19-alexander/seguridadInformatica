package com.seguridadInformatica.spring_boot.repository;

import com.seguridadInformatica.spring_boot.entity.Note;
import com.seguridadInformatica.spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByOwner(User owner);
    Optional<Note> findByIdAndOwner(Long id, User owner);
}