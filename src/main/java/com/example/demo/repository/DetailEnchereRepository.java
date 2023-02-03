package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DetailEnchere;

public interface DetailEnchereRepository extends JpaRepository<DetailEnchere,Long> {
    List<DetailEnchere> findByIdenchere(Long idenchere);
}
