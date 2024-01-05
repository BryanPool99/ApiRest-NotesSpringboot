package com.bryan.backend.service;


import com.bryan.backend.model.Note;
import com.bryan.backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// NoteServiceImpl.java
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(Long id, Note note) {
        Note existingNote = noteRepository.findById(id).orElse(null);

        if (existingNote != null) {
            // Actualiza los campos necesarios
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
            existingNote.setArchived(note.isArchived());

            // Guarda la entidad actualizada en la base de datos
            return noteRepository.save(existingNote);
        }

        return null; // O maneja el caso de entidad no encontrada de alguna manera
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
