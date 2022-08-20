package com.kska.springbootstudy.services;

import com.kska.springbootstudy.models.Note;

import java.util.List;

public interface NoteService {
    Note getNoteById(String id);

    List<Note> getAllNotes();

    void addNote(Note note);

    void updateNote(String id, Note updated);

    void removeNoteById(String id);

    void removeAllNotes();
}
