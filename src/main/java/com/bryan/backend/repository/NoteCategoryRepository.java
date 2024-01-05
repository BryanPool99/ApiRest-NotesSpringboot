package com.bryan.backend.repository;

import com.bryan.backend.model.NoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteCategoryRepository extends JpaRepository<NoteCategory, Long> {
}
