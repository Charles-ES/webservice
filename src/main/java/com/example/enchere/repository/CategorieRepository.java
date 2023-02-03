package com.example.enchere.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.enchere.model.Categorie;

@Repository
public interface CategorieRepository extends CrudRepository<Categorie,Long>{


}
    

