package com.kska.springbootstudy.services;

import com.kska.springbootstudy.models.Note;
import com.kska.springbootstudy.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note getNoteById(String id) {
        return noteRepository.getNoteById(id);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.getAllNotes();
    }

    @Override
    public void addNote(Note note) {
        noteRepository.addNote(note);
    }

    @Override
    public void updateNote(String id, Note updated) {
        noteRepository.updateNote(id, updated);
    }

    @Override
    public void removeNoteById(String id) {
        noteRepository.removeNoteById(id);
    }

    @Override
    public void removeAllNotes() {
        noteRepository.removeAllNotes();
    }
}
