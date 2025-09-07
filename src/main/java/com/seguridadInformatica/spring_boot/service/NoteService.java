package com.seguridadInformatica.spring_boot.service;

import com.seguridadInformatica.spring_boot.dto.NoteDto;
import com.seguridadInformatica.spring_boot.entity.Note;
import com.seguridadInformatica.spring_boot.entity.User;
import com.seguridadInformatica.spring_boot.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(NoteDto dto, User owner) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setOwner(owner);
        return noteRepository.save(note);
    }

    public List<Note> getMyNotes(User owner) {
        return noteRepository.findByOwner(owner);
    }

    public Note getNoteById(Long id, User owner) {
        return noteRepository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new RuntimeException("Note not found or not yours"));
    }

    public Note updateNote(Long id, NoteDto dto, User owner) {
        Note note = getNoteById(id, owner);
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        return noteRepository.save(note);
    }

    public void deleteNote(Long id, User owner) {
        Note note = getNoteById(id, owner);
        noteRepository.delete(note);
    }
}