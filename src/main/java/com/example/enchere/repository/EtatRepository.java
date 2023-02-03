package com.example.enchere.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.enchere.model.Etat;

@Repository
public interface EtatRepository extends CrudRepository<Etat,Long>{

    Etat findByType(String type);

}
    

