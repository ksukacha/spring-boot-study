package com.kska.springbootstudy;

import com.kska.springbootstudy.models.Note;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoteControllerTest {
    private static final String BASE_URL = "http://localhost:";
    private static final String API_NOTES_URL = "/api/notes";
    private static final String ID_PATH_VARIABLE = "/{id}";
    private static final ParameterizedTypeReference<List<Note>> NOTE_LIST_TYPE_REFERENCE =
            new ParameterizedTypeReference<List<Note>>() {};

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    public void whenGetAllNotes_listShouldNotBeEmpty() {
        ResponseEntity<List<Note>> allNotes = getAllNotes();
        assertThat(allNotes.getBody()).isNotEmpty();
    }

    @Test
    @Order(2)
    public void whenAddNote_statusShouldBeCreated() {
        Note note = new Note("testId", "testNoteName", "testNoteDescr");
        ResponseEntity<Void> addedNoteResponse = addNote(note);
        assertThat(addedNoteResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @Order(3)
    public void whenRemoveNote_statusShouldBeNoContent() {
        ResponseEntity<Void> removedNoteResponse = removeNote("testId");
        assertThat(removedNoteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @Order(4)
    public void whenAddNoteAndThenRemoveNote_sizeOfAllNotesShouldBeSame() {
        int originalSize = getCurrentNoteListSize();
        assertThat(originalSize != -1);
        Note note = new Note("testId2", "testNoteName2", "testNoteDescr2");
        addNote(note);
        int newSize = getCurrentNoteListSize();
        assertThat(newSize == originalSize + 1);
        removeNote(note.getId());
        int finalSize = getCurrentNoteListSize();
        assertThat(originalSize == finalSize);
    }

    @Test
    @Order(5)
    public void whenAllNotesRemoved_noteListShouldBeEmpty() {
        removeAllNotes();
        assertThat(getAllNotes().getBody()).isEmpty();
    }

    private ResponseEntity<List<Note>> getAllNotes() {
        return this.restTemplate.exchange(getPath(),
                HttpMethod.GET,
                null,
                NOTE_LIST_TYPE_REFERENCE);
    }

    private int getCurrentNoteListSize() {
        return Optional.ofNullable(getAllNotes())
                .map(ResponseEntity::getBody)
                .map(List::size)
                .orElse(-1);
    }

    private ResponseEntity<Void> addNote(Note note) {
        return this.restTemplate.postForEntity(getPath(), note, Void.class);
    }

    private ResponseEntity<Void> removeNote(String id) {
        return this.restTemplate.exchange(getPath() + ID_PATH_VARIABLE,
                HttpMethod.DELETE,
                null,
                Void.class,
                id);
    }

    private void removeAllNotes() {
        this.restTemplate.exchange(getPath(),
                HttpMethod.DELETE,
                null,
                Void.class);
    }

    private String getPath() {
        return BASE_URL + port + API_NOTES_URL;
    }

}
