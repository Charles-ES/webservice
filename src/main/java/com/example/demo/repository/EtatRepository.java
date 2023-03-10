package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Etat;

public interface EtatRepository extends JpaRepository<Etat, Long>{
    List<Etat> findAll();
    Etat findByType(String type);
}
