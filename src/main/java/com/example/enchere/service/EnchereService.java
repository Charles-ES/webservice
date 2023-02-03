package com.example.enchere.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enchere.model.Enchere;
import com.example.enchere.repository.EnchereRepository;

import lombok.Data;

@Data
@Service

public class EnchereService {
    @Autowired
    private EnchereRepository enchereRepository;

    public Optional<Enchere> getEnchere(final Long id) {
        return enchereRepository.findById(id);
    }

    public Iterable<Enchere> getEncheres() {
        return enchereRepository.findAll();
    }

    public List<Enchere> listerEnchere() {
        return (List<Enchere>) enchereRepository.findAll();
    }

    public void deleteEnchere(final Long id) {
        enchereRepository.deleteById(id);
    }

    public void saveEnchere(Enchere enchere) {
        enchereRepository.save(enchere);
    }
}
