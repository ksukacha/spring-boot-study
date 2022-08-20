package com.kska.springbootstudy.controllers;

import com.kska.springbootstudy.models.Note;
import com.kska.springbootstudy.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable("id") String id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public void addNote(@RequestBody Note note) {
        noteService.addNote(note);
    }

    @PutMapping("/{id}")
    public void updateNote(@PathVariable("id") String id, @RequestBody Note updateNote) {
        noteService.updateNote(id, updateNote);
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable("id") String id) {
        noteService.removeNoteById(id);
    }

    @DeleteMapping
    public void deleteAllNotes() {
        noteService.removeAllNotes();
    }
}
