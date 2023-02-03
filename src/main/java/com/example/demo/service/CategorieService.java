package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Categorie;
import com.example.demo.repository.CategorieRepository;

import lombok.Data;

@Data
@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public Optional<Categorie> getCategorie(final Long id) {
        return categorieRepository.findById(id);
    }

    public List<Categorie> listerCategorie(){
        return (List<Categorie>) categorieRepository.findAll();
    }

    public Iterable<Categorie> getCategories() {
        return categorieRepository.findAll();
    }

    public void deleteCategorie(final Long id) {
        categorieRepository.deleteById(id);
    }

    public Categorie saveCategorie(Categorie compte) {
        Categorie savedCompte = categorieRepository.save(compte);
        return savedCompte;
    }

}
