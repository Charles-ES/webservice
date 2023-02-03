package com.example.enchere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.enchere.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
