package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);
}