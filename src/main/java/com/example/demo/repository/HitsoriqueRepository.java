package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Historique;

public interface HitsoriqueRepository extends JpaRepository<Historique,Long> {
    List<Historique> findByIdenchere(Long idenchere);
}
