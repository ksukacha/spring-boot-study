package com.kska.springbootstudy.services;

import com.kska.springbootstudy.models.Note;

import java.util.List;

public interface NoteService {
    Note getNoteById(Integer id);

    List<Note> getAllNotes();

    void addNote(Note note);

    void updateNote(Integer id, Note updated);

    void removeNoteById(Integer id);

    void removeAllNotes();
}
