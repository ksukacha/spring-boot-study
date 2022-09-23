package com.kska.springbootstudy.services;

import com.kska.springbootstudy.models.Note;
import com.kska.springbootstudy.repositories.NoteRepository;
import com.kska.springbootstudy.repositories.PlainNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class NoteServiceImpl implements NoteService {

    private final PlainNoteRepository plainNoteRepository;

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(PlainNoteRepository plainNoteRepository, NoteRepository noteRepository) {
        this.plainNoteRepository = plainNoteRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public Note getNoteById(Integer id) {
        return Optional.of(noteRepository.findById(id))
                .get().orElse(null);
    }

    @Override
    public List<Note> getAllNotes() {
        Iterable<Note> notes = Optional.of(noteRepository.findAll()).get();
        return StreamSupport.stream(notes.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void addNote(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void updateNote(Integer id, Note updated) {
        noteRepository.save(updated);
    }

    @Override
    public void removeNoteById(Integer id) {
       noteRepository.deleteById(id);
    }

    @Override
    public void removeAllNotes() {
        noteRepository.deleteAll();
    }
}
