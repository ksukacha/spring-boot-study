package com.kska.springbootstudy.repositories;

import com.kska.springbootstudy.models.Note;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class NoteRepository {
    private static final String EMPTY_NOTES = "Notes are empty";

    private List<Note> notes = Stream.of(
            new Note("1", "note1", "abc"),
            new Note("2", "note2", "def")
    ).collect(Collectors.toList());

    public Note getNoteById(String id) {
        if (areNotesEmpty()) {
            log.warn(EMPTY_NOTES);
        }
        return notes.stream()
                .filter(note -> id.equals(note.getId()))
                .findAny()
                .orElse(null);
    }

    public List<Note> getAllNotes() {
        return notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void updateNote(String id, Note updated) {
        Note note = getNoteById(id);
        if (note == null) {
            log.error("No note with id: {}", id);
            return;
        }
        note.setName(updated.getName());
        note.setDescription(updated.getDescription());
    }

    public void removeNoteById(String id) {
        if (areNotesEmpty()) {
            log.warn(EMPTY_NOTES);
            return;
        }
        notes.removeIf(note -> id.equals(note.getId()));
    }

    public void removeAllNotes() {
        if (areNotesEmpty()) {
            log.warn(EMPTY_NOTES);
            return;
        }
        notes.clear();
    }

    private boolean areNotesEmpty() {
        return CollectionUtils.isEmpty(notes);

    }
}
