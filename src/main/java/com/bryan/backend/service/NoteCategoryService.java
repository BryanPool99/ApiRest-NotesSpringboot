package com.bryan.backend.service;

import com.bryan.backend.model.Category;
import com.bryan.backend.model.NoteCategory;

import java.util.List;

public interface NoteCategoryService {
    List<NoteCategory> getAllNotesCategories();
    NoteCategory getNoteCategoryById(Long id);
    NoteCategory saveNoteCategory(NoteCategory noteCategory);
    NoteCategory updateNoteCategory(Long id, NoteCategory noteCategory);
    void deleteNoteCategory(Long id);
}
