package com.bryan.backend.controller;

import com.bryan.backend.model.NoteCategory;
import com.bryan.backend.service.NoteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note-categories")
@CrossOrigin(origins = "*")  // Ajusta según tus necesidades
public class NoteCategoryController {
    private final NoteCategoryService noteCategoryService;
    @Autowired
    public NoteCategoryController(NoteCategoryService noteCategoryService) {
        this.noteCategoryService = noteCategoryService;
    }
    @GetMapping
    public ResponseEntity<List<NoteCategory>> getAllNoteCategories() {
        List<NoteCategory> noteCategories = noteCategoryService.getAllNotesCategories();
        return new ResponseEntity<>(noteCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteCategory> getNoteCategoryById(@PathVariable Long id) {
        NoteCategory noteCategory = noteCategoryService.getNoteCategoryById(id);
        return noteCategory != null
                ? new ResponseEntity<>(noteCategory, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<NoteCategory> createNoteCategory(@RequestBody NoteCategory noteCategory) {
        NoteCategory createdNoteCategory = noteCategoryService.saveNoteCategory(noteCategory);
        return new ResponseEntity<>(createdNoteCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteCategory> updateNoteCategory(
            @PathVariable Long id,
            @RequestBody NoteCategory noteCategory) {
        NoteCategory updatedNoteCategory = noteCategoryService.updateNoteCategory(id, noteCategory);
        return updatedNoteCategory != null
                ? new ResponseEntity<>(updatedNoteCategory, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteCategory(@PathVariable Long id) {
        noteCategoryService.deleteNoteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
