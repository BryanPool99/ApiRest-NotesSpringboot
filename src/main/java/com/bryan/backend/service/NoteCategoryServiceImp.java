package com.bryan.backend.service;

import com.bryan.backend.model.NoteCategory;
import com.bryan.backend.repository.NoteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteCategoryServiceImp implements NoteCategoryService{
    private final NoteCategoryRepository noteCategoryRepository;
    @Autowired
    public NoteCategoryServiceImp(NoteCategoryRepository noteCategoryRepository) {
        this.noteCategoryRepository = noteCategoryRepository;
    }
    @Override
    public List<NoteCategory> getAllNotesCategories() {
        return noteCategoryRepository.findAll();
    }

    @Override
    public NoteCategory getNoteCategoryById(Long id) {
        return noteCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public NoteCategory saveNoteCategory(NoteCategory noteCategory) {
        return noteCategoryRepository.save(noteCategory);
    }

    @Override
    public NoteCategory updateNoteCategory(Long id, NoteCategory noteCategory) {
        NoteCategory existingNoteCategory = noteCategoryRepository.findById(id).orElse(null);

        if (existingNoteCategory != null) {
            // Actualiza los campos necesarios
            existingNoteCategory.setNote(noteCategory.getNote());
            existingNoteCategory.setCategory(noteCategory.getCategory());

            // Guarda la entidad actualizada en la base de datos
            return noteCategoryRepository.save(existingNoteCategory);
        }

        return null; // O maneja el caso de entidad no encontrada de alguna manera
    }

    @Override
    public void deleteNoteCategory(Long id) {
        noteCategoryRepository.deleteById(id);
    }
}
