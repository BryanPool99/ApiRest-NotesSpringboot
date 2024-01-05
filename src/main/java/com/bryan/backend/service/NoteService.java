package com.bryan.backend.service;

import com.bryan.backend.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes();
    Note getNoteById(Long id);
    Note saveNote(Note note);
    Note updateNote(Long id, Note note);
    void deleteNote(Long id);
}