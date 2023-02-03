package com.example.enchere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.enchere.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
