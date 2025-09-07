package com.seguridadInformatica.spring_boot.controller;

import com.seguridadInformatica.spring_boot.dto.NoteDto;
import com.seguridadInformatica.spring_boot.entity.Note;
import com.seguridadInformatica.spring_boot.entity.User;
import com.seguridadInformatica.spring_boot.repository.UserRepository;
import com.seguridadInformatica.spring_boot.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final UserRepository userRepository;

    @Autowired
    public NoteController(NoteService noteService, UserRepository userRepository) {
        this.noteService = noteService;
        this.userRepository = userRepository;
    }

    private User getCurrentUser(UserDetails userDetails) {
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public Note create(@RequestBody @Valid NoteDto dto, @AuthenticationPrincipal UserDetails userDetails) {
        User user = getCurrentUser(userDetails);
        return noteService.createNote(dto, user);
    }

    @GetMapping
    public List<Note> list(@AuthenticationPrincipal UserDetails userDetails) {
        User user = getCurrentUser(userDetails);
        return noteService.getMyNotes(user);
    }

    @GetMapping("/{id}")
    public Note get(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = getCurrentUser(userDetails);
        return noteService.getNoteById(id, user);
    }

    @PutMapping("/{id}")
    public Note update(@PathVariable Long id, @RequestBody @Valid NoteDto dto, @AuthenticationPrincipal UserDetails userDetails) {
        User user = getCurrentUser(userDetails);
        return noteService.updateNote(id, dto, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = getCurrentUser(userDetails);
        noteService.deleteNote(id, user);
    }
}