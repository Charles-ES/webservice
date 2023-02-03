package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Compte;

public interface CompteRepository  extends JpaRepository<Compte,Long>{
    Compte findByIdclient(Long idclient);
}
