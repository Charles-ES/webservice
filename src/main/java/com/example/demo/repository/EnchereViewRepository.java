package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.EnchereView;

public interface EnchereViewRepository extends JpaRepository<EnchereView,Long>{
    List<EnchereView> findByIdetat(Long idetat);
    List<EnchereView> findByIdclient(Long idclient);
    List<EnchereView> findAll();
}
