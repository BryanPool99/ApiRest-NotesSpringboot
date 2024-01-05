package com.bryan.backend.controller;

import com.bryan.backend.model.Note;
import com.bryan.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")  // Ajusta según tus necesidades
public class NoteController {
    private final NoteService noteService;
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    //listar todas las notas
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
    //listar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        return note != null
                ? new ResponseEntity<>(note, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //agregar
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note createdNote = noteService.saveNote(note);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }
    //actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        // Obtén la nota existente por ID
        Note existingNote = noteService.getNoteById(id);

        // Verifica si la nota existe
        if (existingNote != null) {
            // Actualiza los campos de la nota existente con los valores proporcionados en updatedNote
            existingNote.setTitle(updatedNote.getTitle());
            existingNote.setContent(updatedNote.getContent());
            existingNote.setArchived(updatedNote.isArchived());

            // Guarda y devuelve la nota actualizada
            Note savedNote = noteService.updateNote(id, existingNote);
            return new ResponseEntity<>(savedNote, HttpStatus.OK);
        } else {
            // Si la nota no existe, devuelve un estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
